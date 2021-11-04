package ptithcm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javafx.util.Pair;
import ptithcm.bean.FilterCar;
import ptithcm.bean.Mailer;
import ptithcm.bean.PageNumber;
import ptithcm.bean.UploadFile;
import ptithcm.dao.BrandDAO;
import ptithcm.dao.CarDAO;
import ptithcm.dao.OrderDAO;
import ptithcm.dao.TypeDAO;
import ptithcm.entity.Brands;
import ptithcm.entity.Cars;
import ptithcm.entity.Types;
import ptithcm.service.FileService;
import ptithcm.service.PageService;

@Transactional
@Controller
@RequestMapping("/admin/cars/")
public class AdminCarController{
	@Autowired
	UploadFile uploadFile;
	@Autowired
	Mailer mailer;
	@Autowired
	@Qualifier("pagenumber")
	PageNumber pagenumber;
	@Autowired
	FilterCar filterCar;
	
	@Autowired
	PageService pageService;
	@Autowired
	FileService fileService;
	
	
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
		List<Cars> cars = CarDAO.getCars(filterCar);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagenumber.setP(page);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pageService.getPageList(cars, page, 6));
		return "admin/cars";
	}
	
	@RequestMapping(value = "index.htm", params = "btnAdd")
	public String addProduct(HttpServletRequest request,ModelMap model, 
			@ModelAttribute("car") Cars car, @RequestParam(value="imageFile", required=false) MultipartFile imageFile) {
		List<Cars> cars = CarDAO.getCars(filterCar);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("car", car);
		model.addAttribute("pagedListHolder", pageService.getPageList(cars, pagenumber.getP(), 6));
		
		if(imageFile != null) {
			Pair<Boolean, String> result = fileService.uploadFile(uploadFile.getBasePath(), 
					imageFile, car.getName() + "." + imageFile.getContentType().split("/")[1], "image", "car");
			if(!result.getKey()) {
				model.addAttribute("message", result.getValue());
				return "admin/cars";
			}else {
				car.setImg(result.getValue());
			}
		}
		Integer temp = CarDAO.create(car);
		if (temp != 0) {
			model.addAttribute("message", "Insert car successful");
		} else {
			model.addAttribute("message", "Insert car failed! This car maybe already in shop");
		}
		car = new Cars();
		return "admin/cars";
	}
	
	
	@RequestMapping(value="/{id}.htm", params="linkEdit")
	public String edit(HttpServletRequest request, ModelMap model, @PathVariable("id") Integer id, @ModelAttribute("car") Cars car) {
		List<Cars> cars = CarDAO.getCars(filterCar);
		model.addAttribute("btnStatus", "btnEdit");
		model.addAttribute("car", CarDAO.getCar(id));
		model.addAttribute("pagedListHolder", pageService.getPageList(cars, pagenumber.getP(), 6));
		return "admin/cars";
	}
	
	@RequestMapping(value = "index.htm", params = "btnEdit")
	public String edit_Product(HttpServletRequest request, ModelMap model,
			@ModelAttribute("car") Cars car, @RequestParam(value="imageFile", required=false) MultipartFile imageFile) {
		
		List<Cars> cars = CarDAO.getCars(filterCar);
		model.addAttribute("pagedListHolder", pageService.getPageList(cars, pagenumber.getP(), 6));
		
		if(imageFile != null) {
			Pair<Boolean, String> result = fileService.uploadFile(uploadFile.getBasePath(), 
					imageFile, car.getName() + "." + imageFile.getContentType().split("/")[1], "image", "car");
			if(!result.getKey()) {
				model.addAttribute("message", result.getValue());
				return "admin/cars";
			}else {
				car.setImg(result.getValue());
			}
		}
		Integer temp = CarDAO.update(car);
		if (temp != 0) {
			model.addAttribute("message", "Update successfull");
		} else {
			model.addAttribute("message", "Update failed!");
		}
		car = new Cars();			
		return "admin/cars";
	}
	
	@RequestMapping(value = "/{id}.htm", params = "linkDelete")
	public String deleteProduct(HttpServletRequest request, ModelMap model, @ModelAttribute("car") Cars car,
			@PathVariable("id") Integer id) {
		Integer temp = 0;
		if(OrderDAO.getOrders(id).isEmpty()) {
			temp = CarDAO.delete(car);
			if (temp == 1) {
				model.addAttribute("message", "Delete successfull");
			} else if (temp == 0){
				model.addAttribute("message", "Delete failed!");
			}
		}else {
			car = CarDAO.getCar(id);
			car.setAmount(0);
			temp = CarDAO.update(car);
			if (temp == 1) {
				model.addAttribute("message", "Cannot delete completely. This car is used for order infomation. <br> Amount wil be set to 0.");
			} else {
				model.addAttribute("message", "Update amount failed!");
			}
		}
		List<Cars> cars = CarDAO.getCars(filterCar);
		model.addAttribute("btnStatus", "btnAdd");
		model.addAttribute("pagedListHolder", pageService.getPageList(cars, pagenumber.getP(), 6));
		return "admin/cars";
	}

	
	@ModelAttribute("brands")
	public List<Brands> getBrands() {
		List<Brands> list = BrandDAO.getBrands();
		return list;
	}
	
	@ModelAttribute("types")
	public List<Types> getTypes() {
		List<Types> list = TypeDAO.getTypes();
		return list;
	}
	
	@ModelAttribute("brandsSearch")
	public List<Brands> getBrandsSearch() {
		List<Brands> list = BrandDAO.getBrands();
		list.add(0, new Brands("All", "none"));
		return list;
	}
	
	@ModelAttribute("typesSearch")
	public List<Types> getTypesSearch() {
		List<Types> list = TypeDAO.getTypes();
		list.add(0, new Types("All", "none"));
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
