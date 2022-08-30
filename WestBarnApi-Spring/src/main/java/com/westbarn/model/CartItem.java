package com.westbarn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

@Entity
public class CartItem {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cart_id;
	@Column
	@Min(value = 0)
	private int quantity;
	
	//foreign key
	@ManyToOne
	private Customer user;
	
	@ManyToOne
	private Product product;

	public CartItem() {
		super();
	}

	public CartItem(int quantity, Customer user, Product product) {
		super();
		this.quantity = quantity;
		this.user = user;
		this.product = product;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	
}
