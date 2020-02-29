package com.agatTech.MediInstru.MediInstru.product;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

	List<Product> findProductByUserId(int userId);

	//List<Product> getProductList();
	
}
