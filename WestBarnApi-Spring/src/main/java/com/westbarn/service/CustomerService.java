package com.westbarn.service;

import java.util.List;


import com.westbarn.model.Message;
import com.westbarn.model.Bookmark;
import com.westbarn.model.CartItem;
import com.westbarn.model.Customer;
import com.westbarn.model.UserOrder;

public interface CustomerService {
	
	public Iterable<Customer> findAll();
	public Message add(Customer c);
	public Customer find(int id);
	public Customer findByUsername(String username);
	public long count();
	public List<UserOrder> pullOrder(String usernames);
	public List<CartItem> pullCart(String username);
	public Message delete(int id);
	public List<Bookmark> pullBookmark(String username);
}
