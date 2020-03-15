package com.agatTech.MediInstru.MediInstru.customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerModel,Integer> {

	List<CustomerModel> findCustomerByUserId(int userId);
	
	
	//Optional<CustomerModel> findByI(int user_id);

	//List<Product> getProductList();
	
}
