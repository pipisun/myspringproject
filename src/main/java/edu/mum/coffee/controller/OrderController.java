package edu.mum.coffee.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import edu.mum.coffee.domain.Product;
import edu.mum.coffee.repository.OrderRepository;
import edu.mum.coffee.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping({ "/createOrder" })
	public ModelAndView createProductPage() {
		ModelAndView modelAndView = new ModelAndView("createOrder");
		Order order = new Order();
		order.setOrderDate(new Date());
		modelAndView.addObject(order);
		return modelAndView;
	}
	
	@RequestMapping(value="/saveOrder", method=RequestMethod.POST, consumes="application/json", produces="application/json")
	public Order saveOrder(Order order){
		return orderService.save(order);
	}
	
	@RequestMapping(value="/deleteOrder/{id}", method=RequestMethod.GET)
	public void deleteOrder(@PathVariable("id") int id){
		Order order = orderService.findById(id);
		orderService.delete(order);
	}
	
	@RequestMapping(value="/findByProduct", method=RequestMethod.POST, consumes="application/json")
	public List<Order> findByProduct(Product product) {
		return orderService.findByProduct(product);
	}
	
	@RequestMapping(value="/findByPerson", method=RequestMethod.POST, consumes="application/json")
	public List<Order> findByPerson(Person person) {
		return orderService.findByPerson(person);
	}

	public List<Order> findByDate(Date minDate, Date maxDate) {
		return orderService.findByDate(minDate, maxDate);
	}

	@RequestMapping(value="/findOrder/{id}", method=RequestMethod.POST, produces="application/json")
	public Order findById(@PathVariable("id") int id){
		return orderService.findById(id);
	}

	@RequestMapping(value="/findAllOrder", method=RequestMethod.POST, produces="application/json")
	public List<Order> findAll(){
		return orderService.findAll();
	}

}
