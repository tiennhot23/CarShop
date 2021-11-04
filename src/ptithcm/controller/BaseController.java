package ptithcm.controller;

import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.dao.BrandDAO;
import ptithcm.dao.OrderDAO;
import ptithcm.dao.SecurityDAO;
import ptithcm.dao.TypeDAO;
import ptithcm.entity.Brands;
import ptithcm.entity.Orders;
import ptithcm.entity.Securities;
import ptithcm.entity.Types;

@Transactional
@Controller
public class BaseController {
	
	@RequestMapping("index")
	public String index() {
		return "public/index";
	}
	
//	@RequestMapping("login")
//	public String login_form(HttpSession session) {
//		System.out.println(session.getAttribute("username"));
//		if(session.getAttribute("username") != null) return "index";
//		return "login";
//	}
//	
//	@RequestMapping(value="login", method=RequestMethod.POST)
//	public String login(ModelMap model, HttpServletRequest request) {
//		String username = (String) request.getParameter("username");
//		String pass = (String) request.getParameter("pass");
//		if(getAdmin(username, pass) != null) {
//			HttpSession session = request.getSession();
//			session.setAttribute("username", username);
//			session.setAttribute("password", pass);
//			session.setMaxInactiveInterval(60);
//			return "index";
//		}
//		model.addAttribute("message", "User invalid");
//		return "login";
//	}
	
	@RequestMapping("verified")
	public String verified(ModelMap model, @PathParam(value = "token") String token) {
		Securities security = SecurityDAO.getSecurity(token);
		if(security == null) {
			model.addAttribute("status", "0");
			model.addAttribute("message", "Mã xác nhận không tồn tại!");
			return "public/order";
		}
		Long expiredDate = Long.parseLong(security.getExpired());
		Long currentDate = Calendar.getInstance().getTimeInMillis();
		
		if(expiredDate > currentDate) {
			byte[] decodedBytes = Base64.getDecoder().decode(token.getBytes());
			String[] decodeString = new String(decodedBytes).split("~~");
			String oid = decodeString[0];
			Orders order = OrderDAO.getOrder(oid);
			order.setStat(-1);
			Integer temp = OrderDAO.update(order);
			if (temp != 0) {
				model.addAttribute("status", "1");
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
		SecurityDAO.delete(security);
		return "public/order";
	}
	
	
	@ModelAttribute("brands")
	public List<Brands> getBrands() {
		return BrandDAO.getBrands();
	}
	
	@ModelAttribute("types")
	public List<Types> getTypes() {
		return TypeDAO.getTypes();
	}
}


