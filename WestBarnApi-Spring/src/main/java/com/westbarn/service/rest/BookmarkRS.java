package com.westbarn.service.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.westbarn.model.Bookmark;
import com.westbarn.model.Message;
import com.westbarn.service.BookmarkService;

@CrossOrigin()
@RestController
@RequestMapping("/api/bookmark")
public class BookmarkRS {

	@Autowired
	private BookmarkService serv;
	
	@GetMapping("/findAll")
	public Iterable<Bookmark> findAll(){
		return serv.findAll();
	}
	
	@GetMapping("/find/{id}")
	public Bookmark find(@PathVariable int id){
		return serv.find(id);
	}
	
	@PostMapping("/add")
	public Message add(@RequestBody Bookmark b){
		return serv.add(b);
	}
	
	@GetMapping("/addNow/{product_id}+{username}")
	public Message addNow(@PathVariable int product_id,@PathVariable String username)
	{
		return serv.addNow(product_id,username);
	}
	
	@GetMapping("/delete/{id}")
	public Message delete(@PathVariable int id) {
		return serv.delete(id);
	}
}
