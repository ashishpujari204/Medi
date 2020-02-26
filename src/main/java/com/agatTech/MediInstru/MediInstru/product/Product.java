package com.agatTech.MediInstru.MediInstru.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "product")
public class Product {

	private static final long serialVersionUID = 8091879091924046844L;
	
	@Id
	@GeneratedValue
	private int product_id;

	@Column(name="user_id")
	private int userId;
	
	@Column(name="product_name")
	private String product_name;
	
	@Column(name="product_price")
	private String product_price;
	
	@Column(name="gst")
	private String gst;

	
	public Product() {
		super();
	}
	public Product(int product_id, String product_name, String product_price, String gst,int userId) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_price = product_price;
		this.gst = gst;
		this.userId=userId;
	}
	
	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getProduct_price() {
		return product_price;
	}

	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	
	
	
}
