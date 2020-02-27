package com.agatTech.MediInstru.MediInstru.customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerModel,Integer> {

	Optional<CustomerModel> findCustomerByUserId(int user_id);
	
	//Optional<CustomerModel> findByI(int user_id);

	//List<Product> getProductList();
	
}
