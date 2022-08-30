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
import com.westbarn.model.SubCategory;
import com.westbarn.service.SubCategoryService;

@CrossOrigin()
@RestController
@RequestMapping("/api/subcat")
public class SubCategoryRS {
	
	@Autowired
	SubCategoryService serv;
	
	@GetMapping("/findUnder/{name}")
	public List<SubCategory> findUnderCategory(@PathVariable String name){
		return serv.findUnderCategory(name);
	}
	
	@PostMapping("/add")
	public Message add(@RequestBody SubCategory s){
		return serv.add(s);
	}
	
	@GetMapping("/delete/{id}")
	public Message delete(@PathVariable int id) {
		return serv.delete(id);
	}
	
	@GetMapping("/findAll")
	public Iterable<SubCategory> findAll(){
		return serv.findAll();
	}
	
	@GetMapping("/find/{id}")
	public SubCategory find(@PathVariable int id){
		return serv.find(id);
	}

}
