package com.westbarn.service;

import com.westbarn.model.Customer;
import com.westbarn.model.Message;
import com.westbarn.model.Product;
import com.westbarn.model.UserOrder;

public interface OrderService {

	public Iterable<UserOrder> findAll();
	public Message add(UserOrder o);
	public Message addNow(int product_id,int quantity,String username);
	public UserOrder find(int id);
	public Customer pullCustomer(int order_id);
	public Product pullProduct(int order_id);
	public long count();
	public Message delete(int id);
}
