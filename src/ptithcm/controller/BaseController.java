package ptithcm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ptithcm.entity.Admin;

@Transactional
@Controller
public class BaseController {
	@Autowired
	SessionFactory factory;
	
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
