package com.agatTech.MediInstru.MediInstru.User;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name= "user")
public class User implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8091879091924046844L;

	@Id
	@GeneratedValue
	private int userId;

	@Column(name="name")
	private String name;
	
	@Column(name="username")
	private String username;
	
	@Column(name="authToken")
	private String authToken;
	
	@Column(name="mobileNumber")
	private String mobileNumber;
	
	@Column(name="password")
	private String password;

	@Column(name="oldUser")
	private String oldUser;
	
	public User() {
		super();
	}
	 @Override
	    public Set<GrantedAuthority> getAuthorities() {
	        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	        authorities.add(new SimpleGrantedAuthority(this.name));
	        return authorities;
	    }

	public User(int userId, String name, String authToken, String mobileNumber, String password,String oldUser,String username) {
		super();
		this.userId = userId;
		this.name = name;
		this.authToken = authToken;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.oldUser=oldUser;
		this.username=username;
	}


	public String getOldUser() {
		return oldUser;
	}


	public void setOldUser(String oldUser) {
		this.oldUser = oldUser;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAuthToken() {
		return authToken;
	}


	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
}
