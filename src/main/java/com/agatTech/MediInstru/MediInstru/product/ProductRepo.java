package com.agatTech.MediInstru.MediInstru.product;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

	Optional<Product> findProductByUserId(int user_id);

	//List<Product> getProductList();
	
}
