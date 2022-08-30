package com.westbarn.service.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.westbarn.model.Message;
import com.westbarn.model.Product;
import com.westbarn.model.UserOrder;
import com.westbarn.service.ProductService;

@CrossOrigin()
@RestController
@RequestMapping("/api/product")
public class ProductRS {
	
	@Autowired
	private ProductService serv;

	
	@GetMapping("/findAll")
	public Iterable<Product> findAll(){
		return serv.findAll();
	}
	
	@GetMapping("/find/{id}")
	public Product find(@PathVariable int id){
		return serv.find(id);
	}
	
	@PostMapping("/add")
	public Message add(@RequestBody Product p){
		return serv.add(p);
	}
	
	@GetMapping("/count")
	public long count(){
		return serv.count();
	}
	
	@GetMapping("pullOrder/{id}")
	public List<UserOrder> pullOrder(@PathVariable int id){
		return serv.pullOrder(id);
	}
	
	@GetMapping("delete/{id}")
	public Message delete(@PathVariable int id) {
		return serv.delete(id);
	}
	
	@GetMapping("/findByName/{name}")
	public List<Product> findByName(@PathVariable String name){
		return serv.findByProductnameContainsIgnoreCase(name);
	}
}
