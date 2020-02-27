package com.agatTech.MediInstru.MediInstru.myorder;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agatTech.MediInstru.MediInstru.customer.CustomerModel;

@Repository
public interface OrderRepo extends JpaRepository<OrderClass,Integer> {

	List<OrderClass> findOrderByUserId(int user_id);

	//List<Product> getProductList();

}
