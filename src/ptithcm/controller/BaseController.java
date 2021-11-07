package ptithcm.controller;

import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.dao.AdminDAO;
import ptithcm.dao.BrandDAO;
import ptithcm.dao.OrderDAO;
import ptithcm.dao.SecurityDAO;
import ptithcm.dao.TypeDAO;
import ptithcm.entity.Admin;
import ptithcm.entity.Brands;
import ptithcm.entity.Orders;
import ptithcm.entity.Securities;
import ptithcm.entity.Types;
import ptithcm.service.FilterService;

@Transactional
@Controller
public class BaseController {
	@Autowired
	FilterService filterService;
	
	@Autowired
	AdminDAO adminDAO;
	@Autowired
	BrandDAO brandDAO;
	@Autowired
	TypeDAO typeDAO;
	@Autowired
	SecurityDAO securityDAO;
	@Autowired
	OrderDAO orderDAO;
	
	@RequestMapping("index")
	public String index(ModelMap model, HttpServletRequest request) {
		filterService.clearFilterCar();
		filterService.clearFilterOrder();
		String user = (request.getSession().getAttribute("admin")==null && request.getSession().getAttribute("user")==null)?"0":"1";
		model.addAttribute("user", user);
		return "public/index";
	}
	
	@RequestMapping("login")
	public String login_form(HttpSession session, @ModelAttribute("admin") Admin admin) {
		if(session.getAttribute("admin") != null) session.removeAttribute("admin");
		return "login";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(ModelMap model, HttpServletRequest request, @Validated @ModelAttribute("admin")Admin admin, BindingResult err) {
		if(err.hasErrors()) {
			return "login";
		}
		if(adminDAO.getAdmin(admin.getUsername(), admin.getPass()) != null) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", admin);
			session.setMaxInactiveInterval(600);
			return "redirect:/admin/";
		}
		model.addAttribute("message", "User invalid");
		return "login";
	}
	
	@RequestMapping("verified")
	public String verified(ModelMap model, @PathParam(value = "token") String token) {
		Securities security = securityDAO.getSecurity(token);
		if(security == null) {
			model.addAttribute("status", "0");
			model.addAttribute("message", "Mã xác nhận không tồn tại!");
			return "public/notify";
		}
		Long expiredDate = Long.parseLong(security.getExpired());
		Long currentDate = Calendar.getInstance().getTimeInMillis();
		
		if(expiredDate > currentDate) {
			byte[] decodedBytes = Base64.getDecoder().decode(token.getBytes());
			String[] decodeString = new String(decodedBytes).split("~~");
			String oid = decodeString[0];
			Orders order = orderDAO.getOrder(oid);
			order.setStat(-1);
			Integer temp = orderDAO.update(order);
			if (temp != 0) {
				model.addAttribute("status", "1");
				model.addAttribute("order", order);
				model.addAttribute("message", "Đơn hàng của bạn đã được xác nhận.");
			} else {
				model.addAttribute("status", "0");
				model.addAttribute("message", "Không thể cập nhật trạng thái đơn hàng!");
			}			
		}
		else {
			model.addAttribute("status", "0");
			model.addAttribute("message", "Mã xác nhận đã bị hết hạn!");
		}
		securityDAO.delete(security);
		return "public/notify";
	}
	
	
	@ModelAttribute("brands")
	public List<Brands> getBrands() {
		return brandDAO.getBrands();
	}
	
	@ModelAttribute("types")
	public List<Types> getTypes() {
		return typeDAO.getTypes();
	}
}


