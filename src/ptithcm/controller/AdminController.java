package ptithcm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.bean.FilterOrder;
import ptithcm.bean.Mailer;
import ptithcm.bean.PageNumber;
import ptithcm.dao.OrderDAO;
import ptithcm.entity.Orders;

@Transactional
@Controller
@RequestMapping("/admin/")
public class AdminController{
	@Autowired
	SessionFactory factory;
	@Autowired
	Mailer mailer;
	@Autowired
	@Qualifier("pagenumber")
	PageNumber pagenumber;
	@Autowired
	FilterOrder filterOrder;
	
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, ModelMap model, 
			@ModelAttribute("order") Orders order) {
		if(request.getParameter("clear") != null) {
			filterOrder.setOidFilter("");
			filterOrder.setCustomerFilter("");
			filterOrder.setEmailFilter("");
			filterOrder.setPhoneFilter("");
			filterOrder.setStatusFilter(0);
		}
		filterOrder = getFilterOrder(request);
		List<Orders> orders = OrderDAO.getOrders(filterOrder);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagenumber.setP(page);
		model.addAttribute("pagedListHolder", PageController.getPageList(orders, page, 6));
		return "admin/index";
	}
	
	@RequestMapping(value="/{id}.htm", params="linkAccept")
	public String accept(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id,
			@ModelAttribute("order") Orders order) {
		List<Orders> orders = OrderDAO.getOrders(filterOrder);
		model.addAttribute("orderAccept", OrderDAO.getOrder(id));
		model.addAttribute("pagedListHolder", PageController.getPageList(orders, pagenumber.getP(), 6));
		return "admin/index";
	}
	
	@RequestMapping(value = "index", params = "btnAccept")
	public String accepted(HttpServletRequest request, ModelMap model) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		@SuppressWarnings("deprecation")
		String expecteddate = formatter.format(new Date(request.getParameter("expecteddate")));		
		Orders order = OrderDAO.getOrder(Integer.parseInt(request.getParameter("idorderaccept")));
		order.setStat(1);
		Integer temp = OrderDAO.update(order);
		if (temp != 0) {
			model.addAttribute("message", "Order accepted");
		} else {
			model.addAttribute("message", "Failed!");
		}
		String from = "IDRISCAR";
		String to = order.getEmail();
		String subject = "Order Car";
		String body = "Đơn hàng đã được chấp nhận và dự kiến sẽ giao vào ngày " + expecteddate;
		if(temp != 0) {
			try {
				mailer.send(from, to, subject, body);
			}catch (Exception e) {
				model.addAttribute("message","Gửi mail thất bại!");
			}
		}
		List<Orders> orders = OrderDAO.getOrders(filterOrder);
		model.addAttribute("pagedListHolder", PageController.getPageList(orders, pagenumber.getP(), 6));
		
		return "admin/index";
	}
	
	
	@RequestMapping(value="/{id}.htm", params="linkDeny")
	public String deny(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id, @ModelAttribute("order") Orders order) {
		List<Orders> orders = OrderDAO.getOrders(filterOrder);
		model.addAttribute("orderDeny", OrderDAO.getOrder(id));
		model.addAttribute("pagedListHolder", PageController.getPageList(orders, pagenumber.getP(), 6));
		return "admin/index";
	}
	
	@RequestMapping(value = "index", params = "btnDeny")
	public String denied(HttpServletRequest request, ModelMap model) {
		String reason = request.getParameter("disc");
		Orders order = OrderDAO.getOrder(Integer.parseInt(request.getParameter("idorderdeny")));
		order.setStat(2);
		Integer temp = OrderDAO.update(order);
		if (temp != 0) {
			model.addAttribute("message", "Order rejected");
		} else {
			model.addAttribute("message", "Failed!");
		}		
		String from = "IDRISCAR";
		String to = order.getEmail();
		String subject = "Order Car";
		String body = "Đơn hàng đã bị từ chối.<br/>" + ((reason.length()>0)?"Lí do: " + reason + "<br/>":"")
				+ "Mọi thắc mắc vui lòng liên hệ qua website: http://localhost:8080/CarShop/";
		if(temp != 0) {
			try {
				mailer.send(from, to, subject, body);
			}catch (Exception e) {
				model.addAttribute("message","Gửi mail thất bại!");
			}
		}
		List<Orders> orders = OrderDAO.getOrders(filterOrder);
		model.addAttribute("pagedListHolder", PageController.getPageList(orders, pagenumber.getP(), 6));
		return "admin/index";
	}
	
	@ModelAttribute("filter_order")
	public FilterOrder getFilterOrder(HttpServletRequest request) {
		filterOrder.setOidFilter((request.getParameter("oidFilter")==null)?filterOrder.getOidFilter():request.getParameter("oidFilter").trim());
		filterOrder.setCustomerFilter((request.getParameter("customerFilter")==null)?filterOrder.getCustomerFilter():request.getParameter("customerFilter").trim());
		filterOrder.setEmailFilter((request.getParameter("emailFilter")==null)?filterOrder.getEmailFilter():request.getParameter("emailFilter").trim());
		filterOrder.setPhoneFilter((request.getParameter("phoneFilter")==null)?filterOrder.getPhoneFilter():request.getParameter("phoneFilter").trim());
		filterOrder.setStatusFilter((request.getParameter("statusFilter")==null)?filterOrder.getStatusFilter():Integer.parseInt(request.getParameter("statusFilter").trim()));
		return filterOrder;
	}
	
	@ModelAttribute("status")
	public Map<Integer, String> status(){
		Map<Integer, String> map = new HashMap<Integer, String>();
		 map.put(0, "All");
        map.put(-1, "Pending");
        map.put(2, "Denied");
        map.put(1, "Accepted");
		return map;
	}

}
