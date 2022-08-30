package com.westbarn.service;

import java.util.List;

import com.westbarn.model.Message;
import com.westbarn.model.Product;
import com.westbarn.model.UserOrder;

public interface ProductService {
	
	public Iterable<Product> findAll();
	public Message add(Product p);
	public Product find(int id);
	public List<Product> findByProductnameContainsIgnoreCase(String name);
	public long count();
	public List<UserOrder> pullOrder(int product_id);
	public Message delete(int id);
}
