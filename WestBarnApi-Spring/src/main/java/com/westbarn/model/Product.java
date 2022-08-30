package com.westbarn.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Product implements Serializable{

	private static final long serialVersionUID = 9128043295714805155L;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_id;
	@Column
	@NotBlank
	@Size(max = 150)
	private String productname;
	@Column
	private double price;
	@Column
	@NotBlank
	@Size(max = 50)
	private String super_category;
	@Column
	@NotBlank
	@Size(max = 50)
	private String category;
	@Column
	private String sub_category;
	@Column
	private String pic_address; 
	@Column(columnDefinition = "TEXT")
	private String description;
	@Column(columnDefinition = "TEXT")
	private String dimension;
	@Column
	private Boolean sale_status;
	@Column
	private double sale_price;
	
	//foreign key management
	@JsonBackReference
	@OneToMany(mappedBy="product")
	@Cascade(CascadeType.ALL)
	private List<UserOrder> order;

	@JsonBackReference
	@OneToMany(mappedBy="product")
	@Cascade(CascadeType.ALL)
	private List<CartItem> cartItem;
	
	@JsonBackReference
	@OneToMany(mappedBy="product")
	@Cascade(CascadeType.ALL)
	private List<Bookmark> bookmark;
	
	public Product() {
		super();
	}

	public Product(String product_name, double price, String super_category, String category,
			String sub_category) {
		super();
		this.productname = product_name;
		this.price = price;
		this.super_category = super_category;
		this.category = category;
		this.sub_category = sub_category;
		description = "";
		dimension = "";
		sale_status = false;
		sale_price = 0;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String product_name) {
		this.productname = product_name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSuper_category() {
		return super_category;
	}

	public void setSuper_category(String super_category) {
		this.super_category = super_category;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSub_category() {
		return sub_category;
	}

	public void setSub_category(String sub_category) {
		this.sub_category = sub_category;
	}

	public String getPic_address() {
		return pic_address;
	}

	public void setPic_address(String pic_address) {
		this.pic_address = pic_address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public Boolean getSale_status() {
		return sale_status;
	}

	public void setSale_status(Boolean sale_status) {
		this.sale_status = sale_status;
	}

	public double getSale_price() {
		return sale_price;
	}

	public void setSale_price(double sale_price) {
		this.sale_price = sale_price;
	}

	public List<UserOrder> getOrder() {
		return order;
	}
	
	public List<CartItem> getCartItem() {
		return cartItem;
	}

	public List<Bookmark> getBookmark() {
		return bookmark;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + productname + ", price=" + price
				+ ", super_category=" + super_category + ", category=" + category + ", sub_category=" + sub_category
				+ ", pic_address=" + pic_address + ", description=" + description + ", dimension=" + dimension
				+ ", sale_status=" + sale_status + ", sale_price=" + sale_price + ", order=" + order + "]";
	}

	
}
