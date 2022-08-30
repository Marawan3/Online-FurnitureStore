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
import com.westbarn.model.Bookmark;
import com.westbarn.model.CartItem;
import com.westbarn.model.Customer;
import com.westbarn.model.UserOrder;
import com.westbarn.service.CustomerService;

@CrossOrigin()
@RestController
@RequestMapping("/api/customer")
public class CustomerRS {
	
	@Autowired
	private CustomerService serv;

	
	@GetMapping("/findAll")
	public Iterable<Customer> findAll(){
		return serv.findAll();
	}
	
	@GetMapping("/find/{id}")
	public Customer find(@PathVariable int id){
		return serv.find(id);
	}
	
	@GetMapping("/findByUsername/{username}")
	public Customer findByUsername(@PathVariable String username) {
		return serv.findByUsername(username);
	}
	
	//customer will also be added when using sign up service in AuthController
	@PostMapping("/add")
	public Message add(@RequestBody Customer c){
		return serv.add(c);
	}
	
	@GetMapping("/count")
	public long count(){
		return serv.count();
	}
	
	@GetMapping("/pullOrder/{username}")
	public List<UserOrder> pullOrder(@PathVariable String username) {
		return serv.pullOrder(username);
	}
	
	@GetMapping("/pullCart/{username}")
	public List<CartItem> pullCart(@PathVariable String username) {
		return serv.pullCart(username);
	}
	
	@GetMapping("/pullBookmark/{username}")
	public List<Bookmark> pullBookmark(@PathVariable String username) {
		return serv.pullBookmark(username);
	}
}
