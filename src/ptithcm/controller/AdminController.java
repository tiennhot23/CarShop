package ptithcm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ptithcm.bean.FilterOrder;
import ptithcm.bean.Mailer;
import ptithcm.bean.PageNumber;
import ptithcm.entity.Orders;

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
	@Autowired
	FilterOrder filterOrder;
	
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, ModelMap model, 
			@ModelAttribute("order") Orders order) {
		if(request.getParameter("clear") != null) {
			filterOrder.setIdFilter("");
			filterOrder.setCustomerFilter("");
			filterOrder.setEmailFilter("");
			filterOrder.setPhoneFilter("");
			filterOrder.setStatusFilter(0);
		}
		filterOrder = getFilterOrder(request);

		List<Orders> orders = this.getOrders(filterOrder);
		PagedListHolder pagedListHolder = new PagedListHolder(orders);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagenumber.setP(page);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(6);
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/index";
	}
	
	@RequestMapping(value="/{id}.htm", params="linkAccept")
	public String accept(HttpServletRequest request, ModelMap model, @PathVariable("id") String id,
			@ModelAttribute("order") Orders order) {
		
		List<Orders> orders = this.getOrders(filterOrder);
		PagedListHolder pagedListHolder = new PagedListHolder(orders);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		page = pagenumber.getP();
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(6);
		model.addAttribute("orderAccept", getOrder(id));
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/index";
	}
	
	@RequestMapping(value = "index", params = "btnAccept")
	public String accepted(HttpServletRequest request, ModelMap model) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		String expecteddate = formatter.format(new Date(request.getParameter("expecteddate")));
		
		Orders order = getOrder(request.getParameter("idorderaccept"));
		order.setStat(1);
	
		Integer temp = this.updateOrder(order);
		if (temp != 0) {
			model.addAttribute("message", "Order accepted");
		} else {
			model.addAttribute("message", "Failed!");
		}
		
		String from = "tiennhot8@gmail.com";
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
		
		List<Orders> orders = this.getOrders(filterOrder);
		PagedListHolder pagedListHolder = new PagedListHolder(orders);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		page = pagenumber.getP();
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(6);
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		return "admin/index";
	}
	
	
	@RequestMapping(value="/{id}.htm", params="linkDeny")
	public String deny(HttpServletRequest request, ModelMap model, @PathVariable("id") String id, @ModelAttribute("order") Orders order) {
		List<Orders> orders = this.getOrders(filterOrder);
		PagedListHolder pagedListHolder = new PagedListHolder(orders);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		page = pagenumber.getP();
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(6);
		model.addAttribute("orderDeny", getOrder(id));
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/index";
	}
	
	@RequestMapping(value = "index", params = "btnDeny")
	public String denied(HttpServletRequest request, ModelMap model) {
		
		String reason = request.getParameter("disc");
		
		Orders order = getOrder(request.getParameter("idorderdeny"));
		order.setStat(0);
	
		Integer temp = this.updateOrder(order);
		if (temp != 0) {
			model.addAttribute("message", "Order rejected");
		} else {
			model.addAttribute("message", "Failed!");
		}
		
		String from = "tiennhot8@gmail.com";
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
		
		List<Orders> orders = this.getOrders(filterOrder);
		PagedListHolder pagedListHolder = new PagedListHolder(orders);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		page = pagenumber.getP();
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(6);
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		return "admin/index";
	}
	

	
	public List<Orders> getOrders(FilterOrder filterOrder) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Orders where stat <> -2 ";
		if(!filterOrder.getIdFilter().equals("")) hql += "and id = :idFilter ";
		if(!filterOrder.getCustomerFilter().equals("")) hql += "and customer LIKE :customerFilter ";
		if(!filterOrder.getEmailFilter().equals("")) hql += "and email = :emailFilter ";
		if(!filterOrder.getPhoneFilter().equals("")) hql += "and phone = :phoneFilter ";
		if(filterOrder.getStatusFilter() != 0) {
			hql += "and stat = :statusFilter ";
		}
		hql += "order by stat";
				
		Query query = session.createQuery(hql);
		if(!filterOrder.getIdFilter().equals("")) query.setParameter("idFilter", filterOrder.getIdFilter());
		if(!filterOrder.getCustomerFilter().equals("")) query.setParameter("customerFilter", "%" + filterOrder.getCustomerFilter() + "%");
		if(!filterOrder.getEmailFilter().equals("")) query.setParameter("emailFilter", filterOrder.getEmailFilter());
		if(!filterOrder.getPhoneFilter().equals("")) query.setParameter("phoneFilter", filterOrder.getPhoneFilter());
		if(filterOrder.getStatusFilter() != 0) {
			query.setParameter("statusFilter", filterOrder.getStatusFilter());
		}
		
		List<Orders> list = query.list();
		return list;
	}
	
	
	
	
	public Integer insertOrder(Orders order) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		int res = 1;
		try {
			session.save(order);
			t.commit();
		} catch (Exception e) {
			System.out.println(e);
			t.rollback();
			res = 0;
		} finally {
			session.close();
		}
		return res;
	}

	public Integer updateOrder(Orders order) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		int res = 1;
		
		try {
			session.update(order);
			t.commit();
		} catch (Exception e) {
			System.out.println("update error" + e);
			t.rollback();
			res = 0;
		} finally {
			session.close();
		}
		return res;
	}

	public Integer deleteOrder(Orders order) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		int res = 1;
		
		try {
			session.delete(order);
			t.commit();
		} catch (Exception e) {
			System.out.println("update error" + e);
			t.rollback();
			res = 0;
		} finally {
			session.close();
		}
		return res;
	}
	
	
	

	public Orders getOrder(String id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Orders where id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Orders> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}
	
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
	
	public void acceptOrder(String id) {
		
	}
	
	@ModelAttribute("filter_order")
	public FilterOrder getFilterOrder(HttpServletRequest request) {
		filterOrder.setIdFilter((request.getParameter("idFilter")==null)?filterOrder.getIdFilter():request.getParameter("idFilter").trim());
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
