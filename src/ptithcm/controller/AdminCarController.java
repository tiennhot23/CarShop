package ptithcm.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jdk.nashorn.internal.ir.ContinueNode;
import ptithcm.bean.FilterCar;
import ptithcm.bean.Mailer;
import ptithcm.bean.PageNumber;
import ptithcm.entity.Brands;
import ptithcm.entity.Cars;
import ptithcm.entity.Orders;
import ptithcm.entity.Types;

@Transactional
@Controller
@RequestMapping("/admin/cars/")
public class AdminCarController {
	@Autowired
	ServletContext context;
	@Autowired
	SessionFactory factory;
	@Autowired
	Mailer mailer;
	@Autowired
	@Qualifier("pagenumber")
	PageNumber pagenumber;
	@Autowired
	FilterCar filterCar;
	
	
	@RequestMapping("index")
	public String cars(HttpServletRequest request, ModelMap model, @ModelAttribute("car") Cars car) {
		if(request.getParameter("clear") != null) {
			filterCar.setNameFilter("");
			filterCar.setMinFilter(0);
			filterCar.setMaxFilter(Long.parseLong("1000000000000000"));
			filterCar.setTypeFilter("");
			filterCar.setBrandFilter("");
		}
		
		filterCar = getFilterCar(request);
		
		
		List<Cars> cars = this.getCars(filterCar);
		PagedListHolder pagedListHolder = new PagedListHolder(cars);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagenumber.setP(page);
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(6);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		return "admin/cars";
	}
	
	@RequestMapping(value = "index.htm", params = "btnAdd")
	public String addProduct(HttpServletRequest request,ModelMap model, 
			@ModelAttribute("car") Cars car, @RequestParam(value="imageFile", required=false) MultipartFile imageFile) {
		
		if(imageFile == null) {
			
		}else {
			if(!imageFile.getContentType().equals("image/jpeg") && !imageFile.getContentType().equals("image/png")) {
				model.addAttribute("message", "Không đúng định dạng file ảnh");
			}else {
				String photoPath = context.getRealPath("/files/" + car.getName() + "." + imageFile.getContentType().split("/")[1]);
				System.out.println(photoPath);
				try {
					imageFile.transferTo(new File(photoPath));
					car.setImg("files/" + car.getName() + "." + imageFile.getContentType().split("/")[1]);
				}
				catch (Exception e) {
					model.addAttribute("message", "Lỗi lưu file!");
				}
			}
		}
		
		Integer temp = this.insertCar(car);
		if (temp != 0) {
			model.addAttribute("message", "Insert car successful");
		} else {
			model.addAttribute("message", "Insert car failed! This car maybe already in shop");
		}
		car = new Cars();

		
		List<Cars> cars = this.getCars(filterCar);
		PagedListHolder pagedListHolder = new PagedListHolder(cars);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		page = pagenumber.getP();
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(6);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		return "admin/cars";
	}
	
	
	@RequestMapping(value="/{id}.htm", params="linkEdit")
	public String edit(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id, @ModelAttribute("car") Cars car) {
		
		List<Cars> cars = this.getCars(filterCar);
		PagedListHolder pagedListHolder = new PagedListHolder(cars);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		page = pagenumber.getP();
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(6);
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("car", getCar(id));
		model.addAttribute("pagedListHolder", pagedListHolder);
		return "admin/cars";
	}
	
	@RequestMapping(value = "index.htm", params = "btnEdit")
	public String edit_Product(HttpServletRequest request, ModelMap model,
			@ModelAttribute("car") Cars car, @RequestParam(value="imageFile", required=false) MultipartFile imageFile) {

		if(imageFile == null) {
			
		}else {
			if(!imageFile.getContentType().equals("image/jpeg") && !imageFile.getContentType().equals("image/png")) {
				model.addAttribute("message", "Không đúng định dạng file ảnh");
			}else {
				String photoPath = context.getRealPath("/files/" + car.getName() + "." + imageFile.getContentType().split("/")[1]);
				System.out.println(photoPath);
				try {
					imageFile.transferTo(new File(photoPath));
					car.setImg("files/" + car.getName() + "." + imageFile.getContentType().split("/")[1]);
				}
				catch (Exception e) {
					model.addAttribute("message", "Lỗi lưu file!");
				}
			}
		}
		
		
		Integer temp = this.updateCar(car);
		if (temp != 0) {
			model.addAttribute("message", "Update successfull");
		} else {
			model.addAttribute("message", "Update failed!");
		}
		car = new Cars();

		
		List<Cars> cars = this.getCars(filterCar);
		PagedListHolder pagedListHolder = new PagedListHolder(cars);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		page = pagenumber.getP();
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(6);
		model.addAttribute("btnStatus", "btnAdd");
//		model.addAttribute("car", car);
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		return "admin/cars";
	}
	
	@RequestMapping(value = "/{id}.htm", params = "linkDelete")
	public String deleteProduct(HttpServletRequest request, ModelMap model, @ModelAttribute("car") Cars car,
			@PathVariable("id") Integer id) {
		Integer temp = 0;
		
		if(getOrders(id).isEmpty()) {
			temp = this.deleteCar(car);
			if (temp == 1) {
				model.addAttribute("message", "Delete successfull");
			} else if (temp == 0){
				model.addAttribute("message", "Delete failed!");
			}
		}else {
			car = getCar(id);
			car.setAmount(0);
			temp = this.updateCar(car);
//			if (temp == 1) {
//				model.addAttribute("message", "Cannot delete completely. This car is used for order infomation. <br> Amount wil be set to 0.");
//			} else {
//				model.addAttribute("message", "Update amount failed!");
//			}
			model.addAttribute("message", "Cannot delete completely. This car is used for order infomation. <br> Amount wil be set to 0.");
		}
//		temp = this.deleteCar(car);
//		if (temp == 1) {
//			model.addAttribute("message", "Delete successfull");
//		} else if (temp == 0){
//			model.addAttribute("message", "Delete failed!");
//		}
		
		
		List<Cars> cars = this.getCars(filterCar);
		PagedListHolder pagedListHolder = new PagedListHolder(cars);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		page = pagenumber.getP();
		pagedListHolder.setPage(page);
		pagedListHolder.setMaxLinkedPages(5);
		pagedListHolder.setPageSize(6);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		return "admin/cars";
	}
	
	
	
	
	public Cars getCar(int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Cars where id = :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
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
		query.setParameter("search", "%" + filterCar.getNameFilter() + "%");
		query.setParameter("min", filterCar.getMinFilter());
		query.setParameter("max", filterCar.getMaxFilter());
		if(filterCar.getTypeFilter().equals("All")) {
			query.setParameter("type", "%");
		}else query.setParameter("type", "%" + filterCar.getTypeFilter());
		if(filterCar.getBrandFilter().equals("All")) {
			query.setParameter("brand", "%");
		}else query.setParameter("brand", "%" + filterCar.getBrandFilter());
		List<Cars> list = query.list();
		return list;
	}
	
	
	
	
	public Integer insertCar(Cars car) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		int res = 1;
		try {
			session.save(car);
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

	public Integer updateCar(Cars car) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		int res = 1;
		
		try {
			session.update(car);
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

	public Integer deleteCar(Cars car) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		int res = 1;
		
		try {
			session.delete(car);
			t.commit();
		} catch (Exception e) {
//			t.rollback();
//			System.out.println("delete error" + e);
//			if(e.getClass().getSimpleName().equals("ConstraintViolationException")) {
//				Cars c = getCar(car.getId());
//				c.setAmount(0);
//				t = session.beginTransaction();
//				try {
//					
//					session.update(c);
//					System.out.println("here");
//					t.commit();
//					res = 2;
//				}catch (Exception ex) {
//					System.out.println(ex);
//					System.out.println("hore");
//					res = 0;
//					t.rollback();
//				}
//			}
//			else {
//				res = 0;
//			}
			System.out.println("update error" + e);
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
		list.add(0, new Brands("All", "none"));
		return list;
	}
	
	@ModelAttribute("types")
	public List<Types> getTypes() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Types";
		Query query = session.createQuery(hql);
		List<Types> list = query.list();
		list.add(0, new Types("All", "none"));
		return list;
	}
	
	public List<Orders> getOrders(int carId) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Orders where car.id = :carId";
		Query query = session.createQuery(hql);
		query.setParameter("carId", carId);
		List<Orders> list = query.list();
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
