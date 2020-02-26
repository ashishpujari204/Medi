package com.agatTech.MediInstru.MediInstru.JWTController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.agatTech.MediInstru.MediInstru.User.UserNotFoundException;
import com.agatTech.MediInstru.MediInstru.config.JwtTokenUtil;
import com.agatTech.MediInstru.MediInstru.service.JwtUserDetailsService;
import com.agatTech.MediInstru.MediInstru.Model.JwtRequest;
import com.agatTech.MediInstru.MediInstru.Model.JwtResponse;
import com.agatTech.MediInstru.MediInstru.User.User;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		
		authenticate(authenticationRequest.getUsername(), (authenticationRequest.getPassword()));
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		userDetailsService.loadUserByUsernameAndPassword(authenticationRequest.getUsername(),authenticationRequest.getPassword());
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			System.out.println("username-"+username+"-password-"+password);
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		}catch (UsernameNotFoundException e) {
			throw new Exception("Username not found", e);
		}catch (UserNotFoundException e) {
			throw new Exception("User not found", e);
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}catch (LockedException e) {
			System.out.println(e.toString());
		}
	}
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
		return ResponseEntity.ok(userDetailsService.save(user));
	}

	
}
