package edu.mum.coffee.webservice;

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
@RequestMapping("/admin")
public class PersonWebService {
	
	@Autowired
	public OrderService orderService;
	
	@Autowired
	public PersonService personService;

	@GetMapping("/createPerson")
	public ModelAndView createProductPage() {
		ModelAndView modelAndView = new ModelAndView("createPerson");
		Person person = new Person();
		modelAndView.addObject(person);
		return modelAndView;
	}

	@RequestMapping("/savePerson")
	public String saveProductPage(@ModelAttribute(value = "person") Person person) {
		System.out.println(person);
		personService.savePerson(person);
		return "redirect:/admin";
	}

	@RequestMapping("/listPersons")
	public String listProductPage(Map<String, Object> map) {
		List<Person> findAllPersons = personService.findAllPersons();
		map.put("persons", findAllPersons);
		return "listPersons";
	}
	@RequestMapping("/listOrder")
	public String listOrderPage(Map<String, Object> map) {
		List<Order> orders = orderService.findAll();
		map.put("orders", orders);
		return "listOrders";
	}

	@RequestMapping("/updatePerson/{id}")
	public String updateProductsPage(@PathVariable("id") long id, Map<String, Object> map) {
		System.out.println(id);
		Person person = personService.findById(id);
		map.put("person", person);
		return "updatePerson";
	}
	
	@ModelAttribute
	public void getPerson(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		if(id != null){
			map.put("person",personService.findById(Long.valueOf(id)));
		}
	}
	
	@RequestMapping("/updatePersonAndSave")
	public String updatePersonAndSavePage(Person person) {
		personService.savePerson(person);
		System.out.println(person);
		return "redirect:/admin/listPersons";
	}
}
