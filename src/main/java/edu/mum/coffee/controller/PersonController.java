package edu.mum.coffee.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.domain.Order;
import edu.mum.coffee.domain.Person;
import edu.mum.coffee.repository.PersonRepository;
import edu.mum.coffee.service.OrderService;
import edu.mum.coffee.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonService personService;
	@Autowired
	private OrderService orderService;
	
	@GetMapping({ "/createPerson" })
	public ModelAndView createProductPage() {
		ModelAndView modelAndView = new ModelAndView("createPerson");
		Person person = new Person();
		modelAndView.addObject(person);
		return modelAndView;
	}

	@RequestMapping("/listPerson")
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
	
	@RequestMapping(value="/savePerson", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public Person savePerson(Person person) {
		return personService.savePerson(person);
	}

	@RequestMapping(value="/findByEmail/{email}", method=RequestMethod.GET, produces="application/json")
	public List<Person> findByEmail(@PathVariable("email") String email) {
		return personService.findByEmail(email);
	}
	
	@RequestMapping(value="/findById/{id}", method=RequestMethod.GET, produces="application/json")
	public Person findById(Long id) {
		return personService.findById(id);
	}

	@RequestMapping(value="/removePerson", method=RequestMethod.POST, consumes="application/json")
	public void removePerson(Person person) {
		personService.removePerson(person);
	}

}
