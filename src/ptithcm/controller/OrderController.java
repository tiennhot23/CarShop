package ptithcm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.dao.OrderDAO;

@Controller
@RequestMapping("/orders/")
public class OrderController {
	@Autowired
	OrderDAO orderDAO;
	
	
	@RequestMapping("index")
	public String index(ModelMap model, HttpServletRequest request) {
		model.addAttribute("order", orderDAO.getOrder(request.getParameter("oid")));
		String user = (request.getSession().getAttribute("admin")==null && request.getSession().getAttribute("user")==null)?"0":"1";
		model.addAttribute("user", user);
		return "public/orders";
	}
	
}
