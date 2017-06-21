package edu.mum.coffee.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.repository.ProductRepository;
import edu.mum.coffee.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController   {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping({ "/createProduct" })
	public ModelAndView createProductPage() {
		ModelAndView modelAndView = new ModelAndView("createProduct");
		Product product = new Product();
		modelAndView.addObject(product);
		modelAndView.addObject("productType", ProductType.values());
		return modelAndView;
	}
	
	@RequestMapping("/saveProduct")
	public ModelAndView save(@ModelAttribute(value = "product") Product product) {
		productService.save(product);
		ModelAndView mav = new ModelAndView("redirect:/admin");
		return mav;
	}
	
	@RequestMapping("/listProduct")
	public ModelAndView listProductPage(Map<String, Object> map) {
		List<Product> allProduct = productService.getAllProduct();
		map.put("products", allProduct);
		ModelAndView mav = new ModelAndView("listProduct");
		return mav;
	}
	
	@RequestMapping(value="/deleteProduct/{id}")
	public ModelAndView delete(@PathVariable("id") int id) {
		Product product = productService.getProduct(id);
		productService.delete(product);
		ModelAndView mav = new ModelAndView("redirect:/product/listProduct");
		return mav;
	}
	
	@RequestMapping("/updateProduct/{id}")
	public ModelAndView updateProductsPage(@PathVariable("id") int id, Map<String, Object> map) {
		Product product = productService.getProduct(id);
		map.put("product", product);
		map.put("productType", ProductType.values());
		ModelAndView mav = new ModelAndView("updateProduct");
		return mav;
	}
	
	@RequestMapping(value="/getProduct/{productid}", method=RequestMethod.GET, produces="application/json")
	public Product getProduct(@PathVariable("productid") int productId) {
		return  productService.getProduct(productId);
	}
	
	@RequestMapping(value="/getAllProduct", produces="application/json")
	public List<Product> getAllProduct() {
		return  productService.getAllProduct() ;
	}
	
	@RequestMapping(value="/findByTextSearch/{criteria}", method=RequestMethod.GET, produces="application/json")
	public List<Product> findByTextSearch(@PathVariable("productid") String criteria) {
		if (!criteria.contains("%")) {
			criteria = "%"+criteria+"%";
		}
		return productService.findByTextSearch(criteria);
	}

	public List<Product> findByPrice(double minPrice, double maxPrice) {
		return  productService.findByPrice(minPrice, maxPrice);
	}
	
	@RequestMapping(value="/findByProductType", method=RequestMethod.POST, produces="application/json")
	public List<Product> findByProductType(ProductType productType) {
		 return productService.findByProductType(productType);
	}
	
	@ModelAttribute
	public void getProduct(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
		if (id != null) {
			map.put("product", productService.getProduct(id));
			System.out.println("Product was put into map");
		}
		System.out.println("No id");
	}
	
	@RequestMapping(value = "/updateProductAndSave")
	public ModelAndView updateProductsPage(Product product) {
		productService.save(product);
		ModelAndView mav = new ModelAndView("redirect:/product/listProduct");
		return mav;
	}
}
