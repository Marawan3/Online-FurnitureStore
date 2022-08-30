package com.westbarn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.westbarn.dao.ProductDao;
import com.westbarn.model.Message;
import com.westbarn.model.Product;
import com.westbarn.model.UserOrder;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao dao;
	
	@Override
	public Iterable<Product> findAll() {
		return dao.findAll();
	}

	@Override
	public Message add(Product p) {
		Message m=new Message();
		try {
		   dao.save(p);
		   m.setS("Success");
		}catch(Exception ex) {
			m.setS("Error:"+ex);
		}
		return m;
	}

	@Override
	public Product find(int id) {
		Product p = new Product();
		Optional<Product> eop=dao.findById(id);
		if(eop.isPresent()) {
			p=eop.get();
		}
		else {
			System.out.println("findById: item not found");
		}
		return p;
	}
		
	@Override
	public long count() {
		return dao.count();
	}

	@Override
	public List<UserOrder> pullOrder(int product_id) {
		Product p = find(product_id);
		return p.getOrder();
	}

	@Override
	public List<Product> findByProductnameContainsIgnoreCase(String name) {
		return dao.findByProductnameContainsIgnoreCase(name);
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
