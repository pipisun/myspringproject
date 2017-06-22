package edu.mum.coffee.webservice;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.domain.Users;
import edu.mum.coffee.service.ProductService;

@Controller
public class HomeWebService {
	
	@GetMapping({"/", "/home"})
	public String homePage() {
		return "login";
	}
	@GetMapping({ "/index"})
	public String indexPage() {
		return "index";
	}

	@GetMapping({"/secure"})
	public String securePage() {
		return "secure";
	}
//	@RequestMapping(value = "/user")
//	public String toUserPage(@ModelAttribute("user") Person person) {
//		System.out.println("Email:" + person.getEmail());
//		return "userPage";
//	}
	@RequestMapping("/admin")
	public String toAdminPage(){
		return "adminPage";
	}
	
	@RequestMapping("/signup")
	public ModelAndView toSignupPage(Model model){
		ModelAndView modelAndView = new ModelAndView("signup");
		Users user = new Users();
		modelAndView.addObject(user);
		return modelAndView;
	}
	
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        request.getSession().setAttribute("email", principal.getName());
        ModelAndView model = new ModelAndView("userPage");
        return model;
    }
    
//	@RequestMapping("/logout")
//	public String toLoginPage(){
//		return "login";
//	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout,
		HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
			request.getSession().invalidate();
		}
		
		model.setViewName("login");

		return model;
	}
}
