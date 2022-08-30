package com.westbarn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.westbarn.dao.CartDao;
import com.westbarn.model.CartItem;
import com.westbarn.model.Customer;
import com.westbarn.model.Message;
import com.westbarn.model.Product;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartDao dao;
	
	@Autowired
	private ProductService p_serv;
	
	@Autowired
	private CustomerService u_serv;

	@Override
	public Iterable<CartItem> findAll() {
		return dao.findAll();
	}

	@Override
	public Message add(CartItem o) {
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
	public Message addNow(int product_id, int quantity, String username) {
		Message m=new Message();
		Product p = p_serv.find(product_id);
		Customer u = u_serv.findByUsername(username);
		List<CartItem> list = findByUser(u);
		CartItem  c = new CartItem(quantity, u, p);
		
		for(CartItem item: list) {
			if(item.getProduct().equals(p)) {
				c = item;
				item.setQuantity(item.getQuantity()+quantity); 
			}
		}
		

		try {
		   dao.save(c);
		   m.setS("Success");
		}catch(Exception ex) {
			m.setS("Error:"+ex);
		}
		return m;
	}

	@Override
	public CartItem find(int id) {
		CartItem c = new CartItem();
		Optional<CartItem> eop=dao.findById(id);
		if(eop.isPresent()) {
			c=eop.get();
		}
		else {
			System.out.println("findById: item not found");
		}
		return c;
	}
	
	public List<CartItem> findByUser(Customer u) {
		return dao.findByUser(u);
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
	
	public Message changeQuantity(int id,int newQuantity) {
		Message m=new Message();
		CartItem c = find(id);
		if(!dao.existsById(c.getCart_id()))
			m.setS("Error: Item not found.");
		else if(newQuantity<0)
			m.setS("Error: Quantity cannot be below 0.");
		else if(newQuantity==0) {
			dao.delete(c);
			m.setS("Sucess: Item deleted.");
		}
		else if(newQuantity>99)
			m.setS("Error: Largest quantity available is 99.");
		else {
			c.setQuantity(newQuantity);
			try {
				dao.save(c);
				m.setS("Success");
			}catch(Exception ex) {
				m.setS("Error:"+ex);
			}
		}
		return m;
	}

}
