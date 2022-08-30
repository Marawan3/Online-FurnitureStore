package com.westbarn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Bookmark {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookmark_id;
	
	//foreign key
	@ManyToOne
	private Customer user;
	
	@ManyToOne
	private Product product;

	public Bookmark() {
		super();
	}

	public Bookmark( Customer user, Product product) {
		super();
		this.user = user;
		this.product = product;
	}

	public int getBookmark_id() {
		return bookmark_id;
	}

	public void setBookmark_id(int bookmark_id) {
		this.bookmark_id = bookmark_id;
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
