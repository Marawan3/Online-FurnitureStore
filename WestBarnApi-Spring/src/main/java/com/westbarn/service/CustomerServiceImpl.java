package com.westbarn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.westbarn.dao.CustomerDao;
import com.westbarn.model.Message;
import com.westbarn.model.Bookmark;
import com.westbarn.model.CartItem;
import com.westbarn.model.Customer;
import com.westbarn.model.UserOrder;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDao dao;

	@Override
	public Iterable<Customer> findAll() {
		return dao.findAll();
	}

	@Override
	public Message add(Customer c) {
		Message m=new Message();
		try {
		   dao.save(c);
		   m.setS("Success");
		}catch(Exception ex) {
			m.setS("Error:"+ex);
		}
		return m;
	}

	@Override
	public Customer find(int id) {
		Customer c = new Customer();
		Optional<Customer> eop=dao.findById(id);
		if(eop.isPresent()) {
			c=eop.get();
		}
		else {
			System.out.println("findById: item not found");
		}
		return c;
	}

	@Override
	public long count() {
		return dao.count();
	}

	@Override
	public List<UserOrder> pullOrder(String username) {
		Customer c = findByUsername(username);
		return c.getOrder();
	}

	@Override
	public Message delete(int id) {
		Message m=new Message();
		try {
		   dao.deleteById(id);
		   m.setS("Success");
		}catch(Exception ex) {
			m.setS("Error:"+ex);
		}
		return m;
	}

	@Override
	public List<CartItem> pullCart(String username) {
		Customer c = findByUsername(username);
		return c.getCartitem();
	}
	
	@Override
	public List<Bookmark> pullBookmark(String username) {
		Customer c = findByUsername(username);
		return c.getBookmark();
	}

	@Override
	public Customer findByUsername(String username) {
		return dao.findByUsername(username).get(0);
	}

}
