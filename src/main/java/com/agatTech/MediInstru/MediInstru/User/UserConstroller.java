package com.agatTech.MediInstru.MediInstru.User;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserConstroller {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/getUser")
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@GetMapping("/getUserById/{userId}")
	public User getUserById(@PathVariable int userId) {
		Optional<User> task = userRepository.findById(userId);

		if (!task.isPresent())
			throw new UserNotFoundException("id-" + userId);

		return task.get();
	}
	
	@GetMapping("/getUserDetails")
	public User getUserDetails() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="NA";
		if (principal instanceof UserDetails) {
		   username = ((UserDetails)principal).getUsername();
		} else {
		   username = principal.toString();
		}
		User task = userRepository.findByusername(username);

		if (task==null)
			throw new UserNotFoundException("id-" + task.getAuthToken());

		return task;
	}
	

	@GetMapping("/getUserFromNumber/{mobileNumber}")
	public boolean getUserFromNumber(@PathVariable String mobileNumber) {
		Optional<User> task = userRepository.findByMobileNumber(mobileNumber);

		if (!task.isPresent())
			return false;
			//throw new UserNotFoundException("id-" + mobileNumber);

		return task.get().getMobileNumber().equalsIgnoreCase(mobileNumber);
	}
	@GetMapping("/getUserFromUsername/{username}")
	public User getUserFromUsername(@PathVariable String username) {
		User task = userRepository.findByusername(username);
		if (task==null)
			throw new UserNotFoundException("username-" + username);

		return task;
	}

	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}

	@PutMapping("/updateUser/{userId}")
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable int userId) {

		Optional<User> studentOptional = userRepository.findById(userId);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		user.setUserId(userId);
		userRepository.save(user);
		return ResponseEntity.noContent().build();
	}
	
}
