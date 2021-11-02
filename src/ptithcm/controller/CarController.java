package ptithcm.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.bean.FilterCar;
import ptithcm.bean.Mailer;
import ptithcm.bean.PageNumber;
import ptithcm.entity.Brands;
import ptithcm.entity.Cars;
import ptithcm.entity.Orders;
import ptithcm.entity.Securities;
import ptithcm.entity.Types;

@Transactional
@Controller
@RequestMapping("/cars/")
public class CarController {
	@Autowired
	ServletContext context;
	@Autowired
	SessionFactory factory;
	@Autowired
	Mailer mailer;
	@Autowired
	@Qualifier("pagenumber")
	PageNumber pagenumber;
	@Autowired
	FilterCar filterCar;
	
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, ModelMap model) {
		if(request.getParameter("clear") != null) {
			filterCar.setNameFilter("");
			filterCar.setMinFilter(0);
			filterCar.setMaxFilter(Long.parseLong("1000000000000000"));
			filterCar.setTypeFilter("");
			filterCar.setBrandFilter("");
		}else {
			filterCar = getFilterCar(request);
		}
		
		
		
		
		List<Cars> cars = this.getCars(filterCar);
		PagedListHolder pagedListHolder = new PagedListHolder(cars);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagenumber.setP(page);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(8);
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		return "public/cars";
	}
	
	
	@RequestMapping("/order.htm")
	public String order(HttpServletRequest request, ModelMap model) {
		String customer = request.getParameter("customer");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String addres = request.getParameter("addres");
		int amount = Integer.parseInt(request.getParameter("amount"));
		int carid = Integer.parseInt(request.getParameter("carid"));
		int stat = -2;
		Cars car = new Cars();
		car = getCar(carid);
		long total = car.getPrice() * amount;
		
		Orders order = new Orders();
		order.setCustomer(customer);
		order.setEmail(email);
		order.setPhone(phone);
		order.setDatebuy(new Date());
		order.setCar(car);
		order.setAmount(amount);
		order.setAddres(addres);
		order.setTotal(total);
		order.setStat(stat);
		
		if(!addOrder(order)) {
			model.addAttribute("message", "Không thể tạo đơn hàng!");
			return "public/order";
		}
		String token = order.getOid() + "~~" + order.getEmail();
		byte[] encodedBytes = Base64.getEncoder().encode(token.getBytes());
		token = new String(encodedBytes);
		System.out.println("encodedBytes " + new String(encodedBytes));
		byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
		System.out.println("decodedBytes " + new String(decodedBytes));
		
		Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 1);
		Securities securities = new Securities();
		securities.setToken(token);
		securities.setExpired(String.valueOf(c.getTimeInMillis()));
		securities.setOrder(order);
		
		if(!addSecurities(securities)) {
			model.addAttribute("message", "Không thể tạo key!");
			return "public/order";
		}
		
		String from = "IDRISCAR";
		String to = email;
		String subject = "Order Car";
		String body = "";
		try {
			body = "http://localhost:8080/CarShop/verified.htm?token=" + URLEncoder.encode(token, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			mailer.send(from, to, subject, body);
			model.addAttribute("message", "Gửi mail thành công!");
		}catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("message","Gửi mail thất bại!");
		}
		return "public/order";
	}
	
	public List<Cars> getCars(FilterCar filterCar) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Cars WHERE name LIKE :search "
				+ "and price >= :min and price <= :max "
				+ "and type.name LIKE :type "
				+ "and brand.name LIKE :brand ";
		Query query = session.createQuery(hql);
		query.setParameter("search", "%" + filterCar.getNameFilter() + "%");
		query.setParameter("min", filterCar.getMinFilter());
		query.setParameter("max", filterCar.getMaxFilter());
		if(filterCar.getTypeFilter().equals("All")) {
			query.setParameter("type", "%");
		}else query.setParameter("type", "%" + filterCar.getTypeFilter());
		if(filterCar.getBrandFilter().equals("All")) {
			query.setParameter("brand", "%");
		}else query.setParameter("brand", "%" + filterCar.getBrandFilter());
		List<Cars> list = query.list();
		return list;
	}
	public Cars getCar(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Cars where id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Cars> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}
	
	private boolean addOrder(Orders order) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(order);
			transaction.commit();
		}catch (Exception e) {
			System.out.println("addOrder error" + e);
			transaction.rollback();
		}finally {
			session.close();
		}
		return transaction.wasCommitted() ? true : false;
	}
	
	private boolean addSecurities(Securities securities) {
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			session.save(securities);
			transaction.commit();
		}catch (Exception e) {
			transaction.rollback();
		}finally {
			session.close();
		}
		return transaction.wasCommitted() ? true : false;
	}
	
	public Orders getOrder(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Orders where id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Orders> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}
	
	@ModelAttribute("brands")
	public List<Brands> getBrands() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Brands";
		Query query = session.createQuery(hql);
		List<Brands> list = query.list();
		list.add(0, new Brands("All", "none"));
		return list;
	}
	
	@ModelAttribute("types")
	public List<Types> getTypes() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Types";
		Query query = session.createQuery(hql);
		List<Types> list = query.list();
		list.add(0, new Types("All", "none"));
		return list;
	}
	
	@ModelAttribute("filter_car")
	public FilterCar getFilterCar(HttpServletRequest request) {
		filterCar.setNameFilter((request.getParameter("nameFilter")==null)?filterCar.getNameFilter():request.getParameter("nameFilter").trim());
		filterCar.setMinFilter((request.getParameter("minFilter")==null || request.getParameter("minFilter").length()==0)?filterCar.getMinFilter():Long.parseLong(request.getParameter("minFilter").trim()));
		filterCar.setMaxFilter((request.getParameter("maxFilter")==null || request.getParameter("maxFilter").length()==0)?filterCar.getMaxFilter():Long.parseLong(request.getParameter("maxFilter").trim()));
		filterCar.setTypeFilter((request.getParameter("typeFilter")==null)?filterCar.getTypeFilter():request.getParameter("typeFilter").trim());
		filterCar.setBrandFilter((request.getParameter("brandFilter")==null)?filterCar.getBrandFilter():request.getParameter("brandFilter").trim());
		return filterCar;
	}
}
