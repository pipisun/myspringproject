package edu.mum.coffee.webservice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.service.ProductService;

@Controller
public class HomeWebService {
	
	@GetMapping({"/", "/home"})
	public String homePage() {
		return "home";
	}
	@GetMapping({ "/index"})
	public String indexPage() {
		return "index";
	}

	@GetMapping({"/secure"})
	public String securePage() {
		return "secure";
	}
	@RequestMapping("/user")
	public String toUserPage(){
		return "userPage";
	}
	@RequestMapping("/admin")
	public String toAdminPage(){
		return "adminPage";
	}
	
	@RequestMapping("/logout")
	public String toLoginPage(){
		return "login";
	}
	
}
