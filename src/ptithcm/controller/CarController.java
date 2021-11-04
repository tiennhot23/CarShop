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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.bean.FilterCar;
import ptithcm.bean.Mailer;
import ptithcm.bean.PageNumber;
import ptithcm.dao.BrandDAO;
import ptithcm.dao.CarDAO;
import ptithcm.dao.OrderDAO;
import ptithcm.dao.SecurityDAO;
import ptithcm.dao.TypeDAO;
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
		List<Cars> cars = CarDAO.getCars(filterCar);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagenumber.setP(page);
		model.addAttribute("pagedListHolder", PageController.getPageList(cars, page, 8));
		
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
		car = CarDAO.getCar(carid);
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
		
		if(OrderDAO.create(order)==0) {
			model.addAttribute("status", "0");
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
		Securities security = new Securities();
		security.setToken(token);
		security.setExpired(String.valueOf(c.getTimeInMillis()));
		security.setOrder(order);
		
		if(SecurityDAO.create(security)==0) {
			model.addAttribute("status", "0");
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
			e1.printStackTrace();
		}
		try {
			mailer.send(from, to, subject, body);
			model.addAttribute("status", "1");
			model.addAttribute("message", "Đơn hàng của bạn đã được thiết lập. Vui lòng vào mail để xác nhận đơn hàng!");
		}catch (Exception e) {
			model.addAttribute("status", "0");
			model.addAttribute("message","Gửi mail thất bại!");
		}
		return "public/order";
	}
	
	@ModelAttribute("brands")
	public List<Brands> getBrandsSearch() {
		List<Brands> list = BrandDAO.getBrands();
		list.add(0, new Brands("All", "none"));
		return list;
	}
	
	@ModelAttribute("types")
	public List<Types> getTypesSearch() {
		List<Types> list = TypeDAO.getTypes();
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
