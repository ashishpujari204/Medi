package com.agatTech.MediInstru.MediInstru.User;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	  

	  // Enables the distinct flag for the query
	  Optional<User> findByMobileNumber(String mobileNumber);
	  
	  User findByusername(String username);
	  
	  User findByusernameAndPassword(String username,String password);
	 /* List<User> findPeopleDistinctByLastnameOrFirstname(String lastname, String firstname);

	  // Enabling ignoring case for an individual property
	  List<User> findByLastnameIgnoreCase(String lastname);
	  // Enabling ignoring case for all suitable properties
	  List<User> findByLastnameAndFirstnameAllIgnoreCase(String lastname, String firstname);

	  // Enabling static ORDER BY for a query
	  List<User> findByLastnameOrderByFirstnameAsc(String lastname);
	  List<User> findByLastnameOrderByFirstnameDesc(String lastname);*/
}
