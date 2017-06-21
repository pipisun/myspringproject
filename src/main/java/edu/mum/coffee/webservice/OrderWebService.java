package edu.mum.coffee.webservice;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;
import edu.mum.coffee.service.ProductService;

@Controller
@RequestMapping("/user")
public class OrderWebService {
	
	@Autowired
	public PersonService personService;
	
	@Autowired
	public OrderService orderService;

	@GetMapping("/createOrder")
	public ModelAndView createProductPage() {
		ModelAndView modelAndView = new ModelAndView("createOrder");
		Order order = new Order();
		order.setOrderDate(new Date());
		modelAndView.addObject(order);
		return modelAndView;
	}

	@RequestMapping("/saveOrder")
	public String saveProductPage(@ModelAttribute(value = "order") Order order) {
		System.out.println(order);
		orderService.save(order);
		return "redirect:/user";
	}
	
	@RequestMapping("/profile")
	public String profilePage(Map<String,Object> map) {
		List<Person> findByEmail = personService.findByEmail("abc@abc.com");
		map.put("person", findByEmail.get(0));
		return "profile";
	}

	@ModelAttribute
	public void getPerson(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		if(id != null){
			map.put("person",personService.findById(Long.valueOf(id)));
		}
	}
	
	@RequestMapping("/updateProfile")
	public String updatePersonAndSavePage(Person person) {
		personService.savePerson(person);
		System.out.println(person);
		return "redirect:/user";
	}
}
