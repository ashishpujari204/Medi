package com.agatTech.MediInstru.MediInstru.myorder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "myorder")
public class OrderClass extends Auditable<String> {
	@Id
	@GeneratedValue
	private int o_id;

	@Column(name="user_id")
	private int userId;
	
	@Column(name="c_id")
	private int c_id;
	
	@Column(name="p_id")
	private int p_id;
	
	@Column(name="actual_amount")
	private String actual_amount;
	
	@Column(name="gst_amount")
	private String gst_amount;
	
	@Column(name="c_gst")
	private String c_gst;
	
	@Column(name="s_gst")
	private String s_gst;

	@Column(name="total_amount")
	private String total_amount;
	
	@Column(name="no_of_item")
	private String no_of_item;
	
	@Column(name="order_id")
	private String order_id;
	
	

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getNo_of_item() {
		return no_of_item;
	}

	public void setNo_of_item(String no_of_item) {
		this.no_of_item = no_of_item;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public int getO_id() {
		return o_id;
	}

	public void setO_id(int o_id) {
		this.o_id = o_id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	public int getP_id() {
		return p_id;
	}

	public void setP_id(int p_id) {
		this.p_id = p_id;
	}

	public String getActual_amount() {
		return actual_amount;
	}

	public void setActual_amount(String actual_amount) {
		this.actual_amount = actual_amount;
	}

	public String getGst_amount() {
		return gst_amount;
	}

	public void setGst_amount(String gst_amount) {
		this.gst_amount = gst_amount;
	}

	public String getC_gst() {
		return c_gst;
	}

	public void setC_gst(String c_gst) {
		this.c_gst = c_gst;
	}

	public String getS_gst() {
		return s_gst;
	}

	public void setS_gst(String s_gst) {
		this.s_gst = s_gst;
	}
	
	
}
