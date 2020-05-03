package com.agatTech.MediInstru.MediInstru.myorder;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<OrderClass,Integer> {

	List<OrderClass> findOrderByUserId(int user_id);

	@Query(value ="SELECT o.o_id,o.user_id,o.c_id,o.p_id,o.actual_amount,o.gst_amount,o.c_gst,o.s_gst,o.created_date,c.c_name,c.c_address,c.c_h_name,c.c_mobile,p.product_name from myorder o,product p ,customer c where o.user_id=8 AND p.product_id=o.p_id AND c.c_id=o.c_id",nativeQuery = true)
	List<UIOrderClass> findByAllOrderByUserId(int user_id);


	//List<Product> getProductList();

}
