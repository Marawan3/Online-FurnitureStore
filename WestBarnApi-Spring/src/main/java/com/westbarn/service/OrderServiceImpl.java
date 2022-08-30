package com.westbarn.service;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.westbarn.dao.OrderDao;
import com.westbarn.model.Message;
import com.westbarn.model.Product;
import com.westbarn.model.Customer;
import com.westbarn.model.UserOrder;


@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDao dao;
	
	@Autowired
	private ProductService p_serv;
	
	@Autowired
	private CustomerService u_serv;

	@Override
	public Iterable<UserOrder> findAll() {
		return dao.findAll();
	}

	@Override
	public Message add(UserOrder o) {
		Message m=new Message();
		try {
		   dao.save(o);
		   m.setS("Success");
		}catch(Exception ex) {
			m.setS("Error:"+ex);
		}
		return m;
	}

	@Override
	public UserOrder find(int id) {
		UserOrder o = new UserOrder();
		Optional<UserOrder> eop=dao.findById(id);
		if(eop.isPresent()) {
			o=eop.get();
		}
		else {
			System.out.println("findById: item not found");
		}
		return o;
	}

	@Override
	public long count() {
		return dao.count();
	}

	@Override
	public Message addNow(int product_id, int quantity, String username) {
		Message m=new Message();
		Product p = p_serv.find(product_id);
		Customer u = u_serv.findByUsername(username);
		
		UserOrder o = new UserOrder(quantity,new Timestamp(System.currentTimeMillis()),u,p);
		
		try {
		   dao.save(o);
		   m.setS("Success");
		}catch(Exception ex) {
			m.setS("Error:"+ex);
		}
		return m;
	}

	@Override
	public Customer pullCustomer(int order_id) {
		UserOrder o = find(order_id);
		return o.getUser();
	}

	@Override
	public Product pullProduct(int order_id) {
		UserOrder o = find(order_id);
		return o.getProduct();	
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
}
