package ptithcm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ptithcm.bean.FilterCar;
import ptithcm.bean.Mailer;
import ptithcm.entity.Brands;
import ptithcm.entity.Cars;
import ptithcm.entity.Types;

@Transactional
@Controller
@RequestMapping("/admin/")
public class AdminController {
	@Autowired
	SessionFactory factory;
	@Autowired
	Mailer mailer;
	FilterCar filterCar = new FilterCar();
	
	
	@RequestMapping("index")
	public String index() {
		return "admin/index";
	}
	
	@RequestMapping("cars")
	public String cars(HttpServletRequest request, ModelMap model, @ModelAttribute("car") Cars car) {
		getFilterCar(request);
		
		
		
		List<Cars> cars = this.getCars(filterCar);
		PagedListHolder pagedListHolder = new PagedListHolder(cars);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("car", car);
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		return "admin/cars";
	}
	
	
	@RequestMapping(value="cars/{name}.htm", params="linkEdit")
	public String edit(HttpServletRequest request, ModelMap model, @PathVariable("name") String name) {
		
		List<Cars> cars = this.getCars(filterCar);
		PagedListHolder pagedListHolder = new PagedListHolder(cars);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(5);
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("car", getCar(name));
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/cars";
	}
	
	public Cars getCar(String name) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Cars where name = :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		List<Cars> list = query.list();
		if(list.size()>0) return list.get(0);
		else return null;
	}

	public List<Cars> getCars() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Cars";
		Query query = session.createQuery(hql);
		List<Cars> list = query.list();
		return list;
	}
	
	public List<Cars> getCars(FilterCar filterCar) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Cars WHERE name LIKE :search "
				+ "and price >= :min and price <= :max "
				+ "and type.name LIKE :type "
				+ "and brand.name LIKE :brand ";
		Query query = session.createQuery(hql);
		query.setParameter("search", "%" + filterCar.getName() + "%");
		query.setParameter("min", filterCar.getMin());
		query.setParameter("max", filterCar.getMax());
		query.setParameter("type", "%" + filterCar.getType());
		query.setParameter("brand", "%" + filterCar.getBrand());
		List<Cars> list = query.list();
		return list;
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
	
	@ModelAttribute("filter_car")
	public FilterCar getFilterCar(HttpServletRequest request) {
		filterCar.setName((request.getParameter("search")==null)?"":request.getParameter("search").trim());
		filterCar.setMin((request.getParameter("min")==null || request.getParameter("min").length()==0)?0:Long.parseLong(request.getParameter("min").trim()));
		filterCar.setMax((request.getParameter("max")==null || request.getParameter("max").length()==0)?Long.parseLong("1000000000000000"):Long.parseLong(request.getParameter("max").trim()));
		filterCar.setType((request.getParameter("typeSearch")==null)?"":request.getParameter("typeSearch").trim());
		filterCar.setBrand((request.getParameter("brandSearch")==null)?"":request.getParameter("brandSearch").trim());
		return filterCar;
	}
}
