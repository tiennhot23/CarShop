package ptithcm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.corba.se.spi.orbutil.fsm.State;

import ptithcm.bean.FilterCar;
import ptithcm.bean.FilterOrder;
import ptithcm.bean.Mailer;
import ptithcm.bean.PageNumber;
import ptithcm.entity.Brands;
import ptithcm.entity.Cars;
import ptithcm.entity.Orders;
import ptithcm.entity.Types;

@Transactional
@Controller
@RequestMapping("/admin/")
public class AdminController {
	@Autowired
	SessionFactory factory;
	@Autowired
	Mailer mailer;
	@Autowired
	@Qualifier("pagenumber")
	PageNumber pagenumber;
	FilterCar filterCar = new FilterCar();
	FilterOrder filterOrder = new FilterOrder();
	
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, ModelMap model, @ModelAttribute("order") Orders order) {
		getFilterOrder(request);
		
		List<Orders> orders = this.getOrders(filterOrder);
		PagedListHolder pagedListHolder = new PagedListHolder(orders);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagenumber.setP(page);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/index";
	}
	
//	@RequestMapping(value = "cars.htm", params = "btnAdd")
//	public String addProduct(HttpServletRequest request,ModelMap model, 
//			@ModelAttribute("car") Cars car) {
//		Integer temp = this.insertCar(car);
//		if (temp != 0) {
//			model.addAttribute("message", "Insert car successful");
//		} else {
//			model.addAttribute("message", "Insert car failed! This car maybe already in shop");
//		}
//		getFilterCar(request);
//		car = new Cars();
//		
//		List<Cars> cars = this.getCars(filterCar);
//		PagedListHolder pagedListHolder = new PagedListHolder(cars);
//		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
//		page = pagenumber.getP();
//		pagedListHolder.setPage(page);
//		pagedListHolder.setMaxLinkedPages(5);
//		pagedListHolder.setPageSize(5);
//		model.addAttribute("btnStatus", "btnAdd");
//		model.addAttribute("pagedListHolder", pagedListHolder);
//		
//		return "admin/cars";
//	}
//	
//	
//	@RequestMapping(value="cars/{id}.htm", params="linkEdit")
//	public String edit(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id, @ModelAttribute("car") Cars car) {
//		
//		List<Cars> cars = this.getCars(filterCar);
//		PagedListHolder pagedListHolder = new PagedListHolder(cars);
//		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
//		page = pagenumber.getP();
//		pagedListHolder.setPage(page);
//		pagedListHolder.setMaxLinkedPages(5);
//		pagedListHolder.setPageSize(5);
//		model.addAttribute("btnStatus", "btnEdit");
//		model.addAttribute("car", getCar(id));
//		model.addAttribute("pagedListHolder", pagedListHolder);
//		return "admin/cars";
//	}
//	
//	@RequestMapping(value = "cars.htm", params = "btnEdit")
//	public String edit_Product(HttpServletRequest request, ModelMap model,
//			@ModelAttribute("car") Cars car) {
//		Integer temp = this.updateCar(car);
//		if (temp != 0) {
//			model.addAttribute("message", "Update successfull");
//		} else {
//			model.addAttribute("message", "Update failed!");
//		}
//		car = new Cars();
//
//		getFilterCar(request);
//		
//		List<Cars> cars = this.getCars(filterCar);
//		PagedListHolder pagedListHolder = new PagedListHolder(cars);
//		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
//		page = pagenumber.getP();
//		pagedListHolder.setPage(page);
//		pagedListHolder.setMaxLinkedPages(5);
//		pagedListHolder.setPageSize(5);
//		model.addAttribute("btnStatus", "btnAdd");
////		model.addAttribute("car", car);
//		model.addAttribute("pagedListHolder", pagedListHolder);
//		
//		return "admin/cars";
//	}
//	
//	@RequestMapping(value = "/cars/{id}.htm", params = "linkDelete")
//	public String deleteProduct(HttpServletRequest request, ModelMap model, @ModelAttribute("car") Cars car,
//			@PathVariable("id") Integer id) {
//		Integer temp = 0;
//		car = getCar(id);
//		if(getOrders(id).isEmpty()) {
//			temp = this.deleteCar(car);
//			if (temp == 1) {
//				model.addAttribute("message", "Delete successfull");
//			} else if (temp == 0){
//				model.addAttribute("message", "Delete failed!");
//			}
//		}else {
//			car.setAmount(0);
//			temp = this.updateCar(car);
////			if (temp == 1) {
////				model.addAttribute("message", "Cannot delete completely. This car is used for order infomation. <br> Amount wil be set to 0.");
////			} else {
////				model.addAttribute("message", "Update amount failed!");
////			}
//			model.addAttribute("message", "Cannot delete completely. This car is used for order infomation. <br> Amount wil be set to 0.");
//		}
//		
//		getFilterCar(request);
//		
//		List<Cars> cars = this.getCars(filterCar);
//		PagedListHolder pagedListHolder = new PagedListHolder(cars);
//		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
//		page = pagenumber.getP();
//		pagedListHolder.setPage(page);
//		pagedListHolder.setMaxLinkedPages(5);
//		pagedListHolder.setPageSize(5);
//		model.addAttribute("btnStatus", "btnAdd");
//		model.addAttribute("pagedListHolder", pagedListHolder);
//		
//		return "admin/cars";
//	}
//	
//	
//	
//	
//	public Cars getCar(int id) {
//		Session session = factory.getCurrentSession();
//		String hql = "FROM Cars where id = :id";
//		Query query = session.createQuery(hql);
//		query.setParameter("id", id);
//		List<Cars> list = query.list();
//		if(list.size()>0) return list.get(0);
//		else return null;
//	}
//
//	public List<Cars> getCars() {
//		Session session = factory.getCurrentSession();
//		String hql = "FROM Cars";
//		Query query = session.createQuery(hql);
//		List<Cars> list = query.list();
//		return list;
//	}
	
	public List<Orders> getOrders(FilterOrder filterOrder) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Orders ";
		if(!filterOrder.getId().equals("")) hql += "and id = :id ";
		if(!filterOrder.getCustomer().equals("")) hql += "and customer LIKE :customer ";
		if(!filterOrder.getEmail().equals("")) hql += "and email = :email ";
		if(!filterOrder.getPhone().equals("")) hql += "and phone = :phone ";
		if(filterOrder.getStatus() != 2) {
			hql += "and stat = :status ";
		}
		hql = hql.replaceFirst("and", "where");
		hql += "order by stat";
				
		Query query = session.createQuery(hql);
		if(!filterOrder.getId().equals("")) query.setParameter("id", filterOrder.getId());
		if(!filterOrder.getCustomer().equals("")) query.setParameter("customer", "%" + filterOrder.getCustomer() + "%");
		if(!filterOrder.getEmail().equals("")) query.setParameter("email", filterOrder.getEmail());
		if(!filterOrder.getPhone().equals("")) query.setParameter("phone", filterOrder.getPhone());
		if(filterOrder.getStatus() != 2) {
			query.setParameter("status", filterOrder.getStatus());
		}
		
		List<Orders> list = query.list();
		return list;
	}
	
	
	
	
//	public Integer insertCar(Cars car) {
//		Session session = factory.openSession();
//		Transaction t = session.beginTransaction();
//		int res = 1;
//		try {
//			session.save(car);
//			t.commit();
//		} catch (Exception e) {
//			System.out.println(e);
//			t.rollback();
//			res = 0;
//		} finally {
//			session.close();
//		}
//		return res;
//	}
//
//	public Integer updateCar(Cars car) {
//		Session session = factory.openSession();
//		Transaction t = session.beginTransaction();
//		int res = 1;
//		
//		try {
//			session.update(car);
//			t.commit();
//		} catch (Exception e) {
//			System.out.println("update error" + e);
//			t.rollback();
//			res = 0;
//		} finally {
//			session.close();
//		}
//		return res;
//	}
//
//	public Integer deleteCar(Cars car) {
//		Session session = factory.openSession();
//		Transaction t = session.beginTransaction();
//		int res = 1;
//		
//		try {
//			session.delete(car);
//			t.commit();
//		} catch (Exception e) {
////			t.rollback();
////			System.out.println("delete error" + e);
////			if(e.getClass().getSimpleName().equals("ConstraintViolationException")) {
////				Cars c = getCar(car.getId());
////				c.setAmount(0);
////				t = session.beginTransaction();
////				try {
////					
////					session.update(c);
////					System.out.println("here");
////					t.commit();
////					res = 2;
////				}catch (Exception ex) {
////					System.out.println(ex);
////					System.out.println("hore");
////					res = 0;
////					t.rollback();
////				}
////			}
////			else {
////				res = 0;
////			}
//			System.out.println("update error" + e);
//			t.rollback();
//			res = 0;
//		} finally {
//			session.close();
//		}
//		return res;
//	}
//	
//	
//	
//	
//	
//	@ModelAttribute("brands")
//	public List<Brands> getBrands() {
//		Session session = factory.getCurrentSession();
//		String hql = "FROM Brands";
//		Query query = session.createQuery(hql);
//		List<Brands> list = query.list();
//		return list;
//	}
//	
//	@ModelAttribute("types")
//	public List<Types> getTypes() {
//		Session session = factory.getCurrentSession();
//		String hql = "FROM Types";
//		Query query = session.createQuery(hql);
//		List<Types> list = query.list();
//		return list;
//	}
//	
	public List<Orders> getOrders(int carId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Orders where car.id = :carId";
		Query query = session.createQuery(hql);
		query.setParameter("carId", carId);
		List<Orders> list = query.list();
		return list;
	}
	
	public List<Orders> getOrders() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Orders order by stat"; 
		Query query = session.createQuery(hql);
		List<Orders> list = query.list();
		return list;
	}
	
	@ModelAttribute("filter_order")
	public FilterOrder getFilterOrder(HttpServletRequest request) {
		filterOrder.setId((request.getParameter("id")==null)?"":request.getParameter("id").trim());
		filterOrder.setCustomer((request.getParameter("customer")==null)?"":request.getParameter("customer").trim());
		filterOrder.setEmail((request.getParameter("email")==null)?"":request.getParameter("email").trim());
		filterOrder.setPhone((request.getParameter("phone")==null)?"":request.getParameter("phone").trim());
		filterOrder.setStatus((request.getParameter("status")==null)?2:Integer.parseInt(request.getParameter("status").trim()));
		return filterOrder;
	}
	
	@ModelAttribute("status")
	public Map<Integer, String> status(){
		Map<Integer, String> map = new HashMap<Integer, String>();
		 map.put(2, "All");
        map.put(-1, "Pending");
        map.put(0, "Denied");
        map.put(1, "Accepted");
		return map;
	}
}
