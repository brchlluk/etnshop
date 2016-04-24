package cz.etn.etnshop.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cz.etn.etnshop.dao.Product;
import cz.etn.etnshop.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("product/list");
		modelAndView.addObject("count", productService.getProducts().size());
		modelAndView.addObject("products", productService.getProducts());
		return modelAndView;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam(value="expression", required=true) String expression) {
		ModelAndView modelAndView = new ModelAndView("product/list");
		List<Product> productList = productService.searchProducts(expression);
		modelAndView.addObject("count", productList.size());
		modelAndView.addObject("products", productList);
		modelAndView.addObject("expression", expression);
		//let jsp know search was submited
		modelAndView.addObject("searchFlag", 1);
		return modelAndView;
	}

	//handle add and update product
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam(value="id", required=false) String id) {
		ModelAndView modelAndView = new ModelAndView("product/edit");
		Product p;
		//editing new product, validating id before parsing
		if (id == null || id.isEmpty() || !id.matches("[0-9]*")) {
			p = new Product();
			//no other product can have id = 0
			p.setId(0);
			modelAndView.addObject("product", p);
		} 
		//parameter id is valid but there is no record in db
		else if (!productService.isProductExist(Integer.parseInt(id))) {
			modelAndView.setViewName("redirect:edit");
		} 
		//everything is ok, edit product
		else {
			p = productService.getProduct(Integer.parseInt(id));
			modelAndView.addObject("product", p);
		}		
		return modelAndView;
	}

	//handle add and update product
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String updateProduct(@Valid Product product, BindingResult result) {
		//if product is not valid, redirect and show errors
		if (result.hasErrors()) {
			return "product/edit";
        }
		//id == 0 only while adding
		if (product.getId() == 0) {
			productService.saveProduct(product);
		} 
		//update existing product
		else {
			// just in case something goes wrong
			if (!productService.isProductExist(product.getId())) {
				return "redirect:list";
			}
			productService.updateProduct(product);
		}
		return "redirect:list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(String id) {
		//validate id before parsing, check if there is a record in db
		if (id != null && !id.isEmpty() && id.matches("[0-9]*") && productService.isProductExist(Integer.parseInt(id)))
			productService.deleteProduct(Integer.parseInt(id));

		return "redirect:list";
	}

}
