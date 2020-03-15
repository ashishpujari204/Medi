package com.agatTech.MediInstru.MediInstru.myorder;

import java.net.URI;
import java.util.ArrayList;
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
import com.agatTech.MediInstru.MediInstru.customer.CustomerModel;
import com.agatTech.MediInstru.MediInstru.customer.CustomerRepo;
import com.agatTech.MediInstru.MediInstru.product.Product;
import com.agatTech.MediInstru.MediInstru.product.ProductRepo;

@RestController
public class OrderController {
	@Autowired
	private OrderRepo orderRepo;
	
	
	
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@GetMapping("/getOrders")
	public List<OrderClass> getOrder() {
		return orderRepo.findAll();
	}
	
	
	@GetMapping("/getOrderById/{user_id}")
	public List<UIOrderClass> getOrderByUserId(@PathVariable int user_id) {
		List<OrderClass> task = orderRepo.findOrderByUserId(user_id);
		UIOrderClass uiOrderObject;
		List<UIOrderClass> myOrderArray=new ArrayList<>();
		for(int index=0;index<task.size();index++) {
			uiOrderObject=new UIOrderClass();
			OrderClass order = task.get(index);
			Optional<CustomerModel> customerObject=customerRepo.findById(order.getC_id());
			Optional<Product> productObject=productRepo.findById(order.getP_id());
			uiOrderObject.setO_id(order.getO_id());
			uiOrderObject.setUser_id(order.getUserId());
			uiOrderObject.setC_id(order.getC_id());
			uiOrderObject.setP_id(order.getP_id());
			uiOrderObject.setActual_amount(order.getActual_amount());
			uiOrderObject.setGst_amount(order.getGst_amount());
			uiOrderObject.setC_gst(order.getC_gst());
			uiOrderObject.setS_gst(order.getS_gst());
			uiOrderObject.setC_name(customerObject.get().getC_name());
			uiOrderObject.setC_h_name(customerObject.get().getC_h_name());
			uiOrderObject.setC_address(customerObject.get().getC_address());
			uiOrderObject.setC_mobile(customerObject.get().getC_mobile());
			uiOrderObject.setProduct_name(productObject.get().getProduct_name());
			
			myOrderArray.add(uiOrderObject);
		}

		if (myOrderArray.isEmpty())
			throw new OrderNotFoundException("id-" + user_id);

		return myOrderArray;
	}
	//@Query("SELECT e from Employee e where e.employeeName =:name AND e.employeeRole =:role")
	
	
	@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
	public OrderClass saveOrder(@RequestBody List<OrderClass> products) throws Exception {
		OrderClass saveProduct = null;
		for(int index=0;index<products.size();index++) {
			OrderClass product=products.get(index);
			System.out.println("******"+product.getUserId());
			 saveProduct = new OrderClass();
			saveProduct.setC_id(product.getC_id());
			saveProduct.setP_id((product.getP_id()));
			saveProduct.setUserId(product.getUserId());
			saveProduct.setActual_amount((product.getActual_amount()));
			saveProduct.setGst_amount((product.getGst_amount()));
			saveProduct.setC_gst((product.getC_gst()));
			saveProduct.setS_gst((product.getS_gst()));
			saveProduct.setTotal_amount(product.getTotal_amount());
			saveProduct.setNo_of_item(product.getNo_of_item());
			saveProduct.setOrder_id(product.getOrder_id());

			OrderClass saveProducts = orderRepo.save(saveProduct);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(saveProducts.getO_id()).toUri();

			//ResponseEntity.created(location).build();
		}
		return saveProduct;
	}
}
