package com.westbarn.service.rest;

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
import com.westbarn.model.Customer;
import com.westbarn.model.UserOrder;
import com.westbarn.service.OrderService;

@CrossOrigin()
@RestController
@RequestMapping("/api/order")
public class OrderRS {
	
	@Autowired
	private OrderService serv;
	
	@GetMapping("/findAll")
	public Iterable<UserOrder> findAll(){
		return serv.findAll();
	}
	
	@GetMapping("/find/{id}")
	public UserOrder find(@PathVariable int id){
		return serv.find(id);
	}
	
	@PostMapping("/add")
	public Message add(@RequestBody UserOrder p){
		return serv.add(p);
	}
	
	@GetMapping("/addNow/{product_id}+{quantity}+{username}")
	public Message addNow(@PathVariable int product_id,@PathVariable int quantity, @PathVariable String username)
	{
		return serv.addNow(product_id, quantity, username);
		
	}
	
	@GetMapping("/pullUser/{id}")
	public Customer pullCustomer(@PathVariable int id)
	{
		return serv.pullCustomer(id);
	}
	
	@GetMapping("/pullProduct/{id}")
	public Product pullProduct(@PathVariable int id)
	{
		return serv.pullProduct(id);
	}
	
	@GetMapping("/count")
	public long count(){
		return serv.count();
	}
	
	@GetMapping("/delete/{id}")
	public Message delete(@PathVariable int id) {
		return serv.delete(id);
	}
}
