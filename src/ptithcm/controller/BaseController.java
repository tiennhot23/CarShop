package ptithcm.controller;

import org.springframework.http.HttpStatus;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;


import ptithcm.entity.Admin;
import ptithcm.entity.Brands;
import ptithcm.entity.Orders;
import ptithcm.entity.Securities;
import ptithcm.entity.Types;

@Transactional
@Controller
public class BaseController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String index() {
		return "public/index";
	}
	
	@RequestMapping("login")
	public String login_form(HttpSession session) {
		System.out.println(session.getAttribute("username"));
		if(session.getAttribute("username") != null) return "index";
		return "login";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String login(ModelMap model, HttpServletRequest request) {
		String username = (String) request.getParameter("username");
		String pass = (String) request.getParameter("pass");
		if(getAdmin(username, pass) != null) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("password", pass);
			session.setMaxInactiveInterval(60);
			return "index";
		}
		model.addAttribute("message", "User invalid");
		return "login";
	}
	
	@RequestMapping("verified")
	public String verified(ModelMap model, @PathParam(value = "token") String token) {
		Securities securities = getSecurities(token);
		if(securities == null) {
			model.addAttribute("status", "0");
			model.addAttribute("message", "Mã xác nhận không tồn tại!");
			return "public/order";
		}
		Long expiredDate = Long.parseLong(securities.getExpired());
		Long currentDate = Calendar.getInstance().getTimeInMillis();
		
		if(expiredDate > currentDate) {
			byte[] decodedBytes = Base64.getDecoder().decode(token.getBytes());
			String[] decodeString = new String(decodedBytes).split("~~");
			String oid = decodeString[0];
			String email = decodeString[1];
			Orders order = getOrder(oid);
			order.setStat(-1);
			Integer temp = this.updateOrder(order);
			if (temp != 0) {
				model.addAttribute("status", "1");
				model.addAttribute("message", "Đơn hàng của bạn đã được xác nhận.");
			} else {
				model.addAttribute("status", "0");
				model.addAttribute("message", "Không thể cập nhật trạng thái đơn hàng!");
			}
			deleteSecurities(securities);
		}
		else {
			model.addAttribute("status", "0");
			model.addAttribute("message", "Mã xác nhận đã bị hết hạn!");
		}
			
		return "public/order";
	}
	
	public Orders getOrder(String oid) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Orders where oid = :oid";
		Query query = session.createQuery(hql);
		query.setParameter("oid", oid);
		List<Orders> list = query.list();
		session.close();
		if(list.size()>0) return list.get(0);
		else return null;
	}
	
	private Securities getSecurities(String token) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Securities where token = :token";
		Query query = session.createQuery(hql);
		query.setParameter("token", token);
		List<Securities> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}
	
	private Admin getAdmin(String username, String pass) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Admin where username = :username and pass = :pass";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		query.setParameter("pass", pass);
		List<Admin> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}
	
	public Integer updateOrder(Orders order) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		int res = 1;
		try {
			session.update(order);
			t.commit();
		} catch (Exception e) {
			System.out.println("update order error" + e);
			t.rollback();
			res = 0;
		} finally {
			session.close();
		}
		return res;
	}
	
	public Integer deleteSecurities(Securities securities) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		int res = 1;
		
		try {
			session.delete(securities);
			t.commit();
		} catch (Exception e) {
			System.out.println("delete key error" + e);
			t.rollback();
			res = 0;
		} finally {
			session.close();
		}
		return res;
	}
	
	@ModelAttribute("brands")
	public List<Brands> getBrands() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Brands";
		Query query = session.createQuery(hql);
		List<Brands> list = query.list();
		return list;
	}
	
	@ModelAttribute("types")
	public List<Types> getTypes() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Types";
		Query query = session.createQuery(hql);
		List<Types> list = query.list();
		return list;
	}
}


