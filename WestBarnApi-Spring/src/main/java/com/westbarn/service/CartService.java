package com.westbarn.service;

import com.westbarn.model.CartItem;
import com.westbarn.model.Message;

public interface CartService {
	public Iterable<CartItem> findAll();
	public Message add(CartItem o);
	public Message addNow(int product_id,int quantity,String username);
	public CartItem find(int id);
	public Message delete(int id);
	public Message changeQuantity(int id,int newQuantity);
}
