package com.agatTech.MediInstru.MediInstru.myorder;

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
public class OrderController {
	@Autowired
	private OrderRepo orderRepo;
	
	@GetMapping("/getOrders")
	public List<OrderClass> getOrder() {
		return orderRepo.findAll();
	}
	@GetMapping("/getOrderById/{user_id}")
	public OrderClass getOrderByUserId(@PathVariable int user_id) {
		Optional<OrderClass> task = orderRepo.findOrderByUserId(user_id);

		if (task.isPresent())
			throw new UserNotFoundException("id-" + user_id);

		return task.get();
	}
	//@Query("SELECT e from Employee e where e.employeeName =:name AND e.employeeRole =:role")
	
	
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	public OrderClass saveOrder(@RequestBody OrderClass product) throws Exception {
			System.out.println("******"+product.getUserId());
			OrderClass saveProduct = new OrderClass();
			saveProduct.setC_id(product.getC_id());
			saveProduct.setP_id((product.getP_id()));
			saveProduct.setUserId(product.getUserId());
			saveProduct.setActual_amount((product.getActual_amount()));
			saveProduct.setGst_amount((product.getGst_amount()));
			saveProduct.setC_gst((product.getC_gst()));
			saveProduct.setS_gst((product.getS_gst()));
			OrderClass saveProducts = orderRepo.save(saveProduct);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(saveProducts.getO_id()).toUri();

			return saveProduct;//ResponseEntity.created(location).build();
		
	}
}
