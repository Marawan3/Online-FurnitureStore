package com.westbarn.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UserOrder implements Serializable{

	private static final long serialVersionUID = -2195188596597379026L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int order_id;
	@Column
	private int quantity;
	@Column
	private Timestamp order_date;
	@Column
	private boolean shipping_status;
	@Column
	private Timestamp shipping_date;
	@Column
	private String tracking_number;
	@Column
	private boolean complete_status;
	
	//foreign key
	@ManyToOne
	private Customer user;
	
	@ManyToOne
	private Product product;


	public UserOrder() {
		super();
	}


	public UserOrder(int quantity, Timestamp order_date, Customer user, Product product) {
		super();
		this.quantity = quantity;
		this.order_date = order_date;
		this.user = user;
		this.product = product;
		shipping_status = false;
		complete_status = false;
	}


	public int getOrder_id() {
		return order_id;
	}


	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Timestamp getOrder_date() {
		return order_date;
	}


	public void setOrder_date(Timestamp order_date) {
		this.order_date = order_date;
	}


	public boolean isShipping_status() {
		return shipping_status;
	}


	public void setShipping_status(boolean shipping_status) {
		this.shipping_status = shipping_status;
	}


	public Timestamp getShipping_date() {
		return shipping_date;
	}


	public void setShipping_date(Timestamp shipping_date) {
		this.shipping_date = shipping_date;
	}


	public String getTracking_number() {
		return tracking_number;
	}


	public void setTracking_number(String tracking_number) {
		this.tracking_number = tracking_number;
	}


	public boolean isComplete_status() {
		return complete_status;
	}


	public void setComplete_status(boolean complete_status) {
		this.complete_status = complete_status;
	}


	public Customer getUser() {
		return user;
	}


	public void setUser(Customer user) {
		this.user = user;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", quantity=" + quantity + ", order_date=" + order_date
				+ ", shipping_status=" + shipping_status + ", shipping_date=" + shipping_date + ", tracking_number="
				+ tracking_number + ", complete_status=" + complete_status + ", user=" + user + ", product=" + product
				+ "]";
	}
	
	

}
