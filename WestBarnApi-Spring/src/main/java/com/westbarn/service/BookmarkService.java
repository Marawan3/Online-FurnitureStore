package com.westbarn.service;

import java.util.List;

import com.westbarn.model.Bookmark;
import com.westbarn.model.Customer;
import com.westbarn.model.Message;

public interface BookmarkService {
	public Iterable<Bookmark> findAll();
	public Message add(Bookmark o);
	public Message addNow(int product_id,String username);
	public Bookmark find(int id);
	public List<Bookmark> findByUser(Customer c);
	public Message delete(int id);
}
