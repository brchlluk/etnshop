package cz.etn.etnshop.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cz.etn.etnshop.dao.Product;
import cz.etn.etnshop.service.ProductService;

@Controller
@RequestMapping("/product")
@SessionAttributes("search")
public class ProductController {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "search", required = false) String search) {
		ModelAndView modelAndView = new ModelAndView("product/list");
		if (search == null) {
			modelAndView.addObject("count", productService.getProducts().size());
			modelAndView.addObject("products", productService.getProducts());
			modelAndView.addObject("search", "");
		} else {
			List<Product> productList = productService.searchProducts(search);
			modelAndView.addObject("count", productList.size());
			modelAndView.addObject("products", productList);
			//set session to search expression
			modelAndView.addObject("search", search);
		}
		return modelAndView;
	}

	// handle add and update product
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String add(@ModelAttribute("search") String search, @RequestParam(value = "id", required = false) String id, ModelMap model) {
		Product p;
		// adding new product
		if (id == null || id.isEmpty()) {
			p = new Product();
			// no other product can have id = 0
			p.setId(0);
			model.addAttribute("product", p);
		}
		// parameter id is not valid or not in db
		else if (!id.matches("[0-9]*") || !productService.isProductExist(Integer.parseInt(id))) {
			model.clear();
			return "redirect:edit";
		}
		// everything is ok, edit product
		else {
			p = productService.getProduct(Integer.parseInt(id));
			model.addAttribute("product", p);
		}
		return "product/edit";
	}

	// handle add and update product
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute("search") String search, 
						 @Valid @ModelAttribute("product") Product product, BindingResult result, 
						 ModelMap model) {		
		// if product is not valid, redirect and show errors
		if (result.hasErrors()) {
			return "product/edit";
		}
		// id == 0 only while adding
		if (product.getId() == 0) {
			productService.saveProduct(product);
		}
		// update existing product
		else {
			// just in case something goes wrong
			if (productService.isProductExist(product.getId())) {
				productService.updateProduct(product);
			}
		}
		// if deleting without filter, don't write anything into URL
		if (search == "") {
			model.clear();
			return "redirect:/product/list";
		}
		// redirect to list with applied filter
		else {
			return "redirect:/product/list?search={search}";
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@ModelAttribute("search") String search, String id, ModelMap model) {
		// validate id before parsing, check if there is a record in db
		if (id != null && !id.isEmpty() && id.matches("[0-9]*") && productService.isProductExist(Integer.parseInt(id)))
			productService.deleteProduct(Integer.parseInt(id));
		// if deleting without filter, don't write anything into URL
		if (search == "") {
			model.clear();
			return "redirect:/product/list";
		}
		// redirect to list with applied filter
		else {
			return "redirect:/product/list?search={search}";
		}
	}
}

