package com.agatTech.MediInstru.MediInstru.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "customer")
public class CustomerModel {

private static final long serialVersionUID = 8091879091924046844L;
	
	@Id
	@GeneratedValue
	private int c_id;

	@Column(name="user_id")
	private int userId;
	
	@Column(name="c_name")
	private String c_name;

	@Column(name="c_address")
	private String c_address;
	
	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_address() {
		return c_address;
	}

	public void setC_address(String c_address) {
		this.c_address = c_address;
	}

	public String getC_h_name() {
		return c_h_name;
	}

	public void setC_h_name(String c_h_name) {
		this.c_h_name = c_h_name;
	}

	public String getC_mobile() {
		return c_mobile;
	}

	public void setC_mobile(String c_mobile) {
		this.c_mobile = c_mobile;
	}

	@Column(name="c_h_name")
	private String c_h_name;
	
	@Column(name="c_mobile")
	private String c_mobile;
	
	
}
