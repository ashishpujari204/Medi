package com.agatTech.MediInstru.MediInstru.product;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.agatTech.MediInstru.MediInstru.User.UserNotFoundException;

@RestController
public class ProductController {

	@Autowired
	private ProductRepo productRepo;
	
	@GetMapping("/getProduct")
	public List<Product> getAllUser() {
		return productRepo.findAll();
	}
	@GetMapping("/getProductById/{user_id}")
	public Product getProductById(@PathVariable int user_id) {
		Optional<Product> task = productRepo.findProductByUserId(user_id);

		if (task.isPresent())
			throw new UserNotFoundException("id-" + user_id);

		return task.get();
	}
	
	@RequestMapping(value = "/createProduct", method = RequestMethod.POST)
	public Product saveProduct(@RequestBody Product product) throws Exception {
			System.out.println("******"+product.getUserId());
			Product saveProduct = new Product();
			saveProduct.setUserId(product.getUserId());
			saveProduct.setGst((product.getGst()));
			saveProduct.setProduct_name(product.getProduct_name());
			saveProduct.setProduct_price((product.getProduct_price()));
			Product saveProducts = productRepo.save(saveProduct);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(saveProducts.getProduct_id()).toUri();

			return saveProduct;//ResponseEntity.created(location).build();
		
	}
}