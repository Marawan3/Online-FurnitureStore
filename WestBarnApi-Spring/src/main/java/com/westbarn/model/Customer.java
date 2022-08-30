package com.westbarn.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Customer implements Serializable{

	private static final long serialVersionUID = -5190603756884121239L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	@Column(unique = true)
	@NotBlank
	@Size(max = 20)
	private String username;
	@Column
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
	//foreign key management
	@JsonBackReference
	@OneToMany(mappedBy="user")
	@Cascade(CascadeType.ALL)
	private List<UserOrder> order;
	
	@JsonBackReference
	@OneToMany(mappedBy="user")
	@Cascade(CascadeType.ALL)
	private List<CartItem> cartitem;
	
	@JsonBackReference
	@OneToMany(mappedBy="user")
	@Cascade(CascadeType.ALL)
	private List<Bookmark> bookmark;
	
	public Customer() {
		super();
	}
	
	public Customer(String username, String email) {
		super();
		this.username = username;
		this.email = email;
	}


	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setOrder(List<UserOrder> order) {
		this.order = order;
	}

	public List<UserOrder> getOrder() {
		return order;
	}

	public List<CartItem> getCartitem() {
		return cartitem;
	}

	public void setCartitem(List<CartItem> cartitem) {
		this.cartitem = cartitem;
	}

	public List<Bookmark> getBookmark() {
		return bookmark;
	}

	public void setBookmark(List<Bookmark> bookmark) {
		this.bookmark = bookmark;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", email=" + email + ", username=" + username + ", order=" + order + "]";
	}
	
}
