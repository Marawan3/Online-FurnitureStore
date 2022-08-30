package com.westbarn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.westbarn.dao.BookmarkDao;
import com.westbarn.model.Bookmark;
import com.westbarn.model.Customer;
import com.westbarn.model.Message;
import com.westbarn.model.Product;

@Service
public class BookmarkServiceImpl implements BookmarkService{

	@Autowired
	private BookmarkDao dao;
	
	@Autowired
	private ProductService p_serv;
	
	@Autowired
	private CustomerService u_serv;
	
	@Override
	public Message add(Bookmark b) {
		Message m=new Message();
		try {
		   dao.save(b);
		   m.setS("Success");
		}catch(Exception ex) {
			m.setS("Error:"+ex);
		}
		return m;
	}

	@Override
	public Message addNow(int product_id,String username) {
		Message m=new Message();
		Product p = p_serv.find(product_id);
		Customer u = u_serv.findByUsername(username);
		List<Bookmark> list = findByUser(u);
		Bookmark  c = new Bookmark(u, p);
		
		for(Bookmark mark: list) {
			if(mark.getProduct().equals(p)) {
				m.setS("Success");
				return m;
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
	public Bookmark find(int id) {
		Bookmark b = new Bookmark();
		Optional<Bookmark> eop=dao.findById(id);
		if(eop.isPresent()) {
			b=eop.get();
		}
		else {
			System.out.println("findById: item not found");
		}
		return b;
	}
	
	public List<Bookmark> findByUser(Customer u) {
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

	@Override
	public Iterable<Bookmark> findAll() {
		return dao.findAll();
	}
}
