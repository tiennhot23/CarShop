package ptithcm.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sun.org.apache.bcel.internal.generic.NEW;

import ptithcm.entity.Admin;
import ptithcm.entity.Orders;
import ptithcm.entity.Securities;

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
		if(securities!=null && securities.getExpired().after(new Date())) {
			byte[] decodedBytes = Base64.getDecoder().decode(token.getBytes());
			String[] decodeString = new String(decodedBytes).split("~~");
			String oid = decodeString[0];
			String email = decodeString[1];
			model.addAttribute("message", "Xác minh thành công!");
		}
		else
			model.addAttribute("message", "Mã xác nhận không tồn tại hoặc đã bị hết hạn!");
		return "public/order";
	}
	
	public Orders getOrder(String oid) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Orders where oid = :oid";
		Query query = session.createQuery(hql);
		query.setParameter("oid", oid);
		List<Orders> list = query.list();
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
}
