package com.agatTech.MediInstru.MediInstru.customer;

import java.net.URI;
import java.util.List;

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
public class CustomerConstoller {

	@Autowired
	private CustomerRepo customerRepo;
	
	@GetMapping("/getCustomer")
	public List<CustomerModel> getAllUser() {
		return customerRepo.findAll();
	}
	@GetMapping("/getCustomerById/{userId}")
	public List<CustomerModel> getProductById(@PathVariable int userId) {
		List<CustomerModel> task = customerRepo.findCustomerByUserId(userId);

		if (task.isEmpty())
			throw new UserNotFoundException("id-" + userId);

		return task;
	}
	
	@RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
	public CustomerModel saveProduct(@RequestBody CustomerModel product) throws Exception {
			System.out.println("******"+product.getUserId());
			CustomerModel saveCustomer = new CustomerModel();
			saveCustomer.setUserId(product.getUserId());
			saveCustomer.setC_name((product.getC_name()));
			saveCustomer.setC_address(product.getC_address());
			saveCustomer.setC_h_name((product.getC_h_name()));
			saveCustomer.setC_mobile((product.getC_mobile()));
			CustomerModel saveProducts = customerRepo.save(saveCustomer);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(saveProducts.getC_id()).toUri();

			return saveCustomer;//ResponseEntity.created(location).build();
		
	}
}
