package ptithcm.controller;

import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Transactional
@Controller
@RequestMapping("/orders/")
public class OrderController {
	@RequestMapping("index")
	public String index() {
		return "public/orders";
	}
}
