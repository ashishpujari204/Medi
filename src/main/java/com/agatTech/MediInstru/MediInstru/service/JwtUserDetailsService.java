package com.agatTech.MediInstru.MediInstru.service;


import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.agatTech.MediInstru.MediInstru.User.TokenGenerator;
import com.agatTech.MediInstru.MediInstru.User.User;
import com.agatTech.MediInstru.MediInstru.User.UserNotFoundException;
import com.agatTech.MediInstru.MediInstru.User.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 try {
			 	User  user = userRepository.findByusername(username);
			 	
	            if (user == null) {
	    			throw new UsernameNotFoundException("User not found with username: " + username);
	    		}
	            return new User(user.getUserId(),user.getName(),user.getAuthToken(),user.getMobileNumber(),user.getPassword(),user.getOldUser(),user.getUserName());
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	}
	
	public User loadUserByUsernameAndPassword(String username,String password) throws UserNotFoundException {
		 try {
			 	User  user = userRepository.findByusernameAndPassword(username, password);
			 	
	            if (user == null) {
	    			throw new UserNotFoundException("User not found with username: " + username);
	    		}
	            return new User(user.getUserId(),user.getName(),user.getAuthToken(),user.getMobileNumber(),user.getPassword(),user.getOldUser(),user.getUserName());
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	}
	
	public User save(User user) {
		TokenGenerator tokenCreator=new TokenGenerator();
		if(!getUserFromNumber(user.getMobileNumber())) {
			System.out.println("password is---"+user.getPassword());
			User updatedUser = new User();
			updatedUser.setAuthToken(tokenCreator.generateToken(user.getMobileNumber()));
			//updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));
			updatedUser.setPassword((user.getPassword()));
			updatedUser.setMobileNumber(user.getMobileNumber());
			updatedUser.setName(user.getName());
			updatedUser.setUserName(user.getUsername());
			updatedUser.setOldUser("No");
			User savedUser = userRepository.save(updatedUser);

			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(savedUser.getUserId()).toUri();

			return updatedUser;//ResponseEntity.created(location).build();
		}else {
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(user.getUserId()).toUri();
			user.setAuthToken(tokenCreator.generateToken(user.getMobileNumber()));
			user.setOldUser("Yes");
			user.setUserName(user.getUsername());
			return user;//ResponseEntity.created(location).build();
		}

	}
	@GetMapping("/getUser/{mobileNumber}")
	public boolean getUserFromNumber(@PathVariable String mobileNumber) {
		Optional<User> task = userRepository.findByMobileNumber(mobileNumber);

		if (!task.isPresent())
			return false;
			//throw new UserNotFoundException("id-" + mobileNumber);

		return task.get().getMobileNumber().equalsIgnoreCase(mobileNumber);
	}
	
	

}
