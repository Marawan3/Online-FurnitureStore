package com.westbarn.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.westbarn.model.CartItem;
import com.westbarn.model.Message;
import com.westbarn.service.CartService;

@CrossOrigin()
@RestController
@RequestMapping("/api/cart")
public class CartRS {

	@Autowired
	private CartService serv;
	
	@GetMapping("/findAll")
	public Iterable<CartItem> findAll(){
		return serv.findAll();
	}
	
	@GetMapping("/find/{id}")
	public CartItem find(@PathVariable int id){
		return serv.find(id);
	}
	
	@PostMapping("/add")
	public Message add(@RequestBody CartItem c){
		return serv.add(c);
	}
	
	@GetMapping("/addNow/{product_id}+{quantity}+{username}")
	public Message addNow(@PathVariable int product_id,@PathVariable int quantity, @PathVariable String username)
	{
		return serv.addNow(product_id, quantity, username);
	}
	
	@GetMapping("/delete/{id}")
	public Message delete(@PathVariable int id) {
		return serv.delete(id);
	}
	
	@GetMapping("/changeQuantity/{id}+{newQuantity}")
	public Message changeQuantity(@PathVariable int id,@PathVariable int newQuantity) {
		return serv.changeQuantity(id, newQuantity);
	}
}
