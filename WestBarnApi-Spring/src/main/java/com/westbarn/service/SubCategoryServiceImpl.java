package com.westbarn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.westbarn.dao.SubCategoryDao;
import com.westbarn.model.Message;
import com.westbarn.model.SubCategory;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{
	
	@Autowired
	private SubCategoryDao dao;

	@Override
	public List<SubCategory> findUnderCategory(String catName) {
		String clean = catName.replaceAll("(.)([A-Z])", "$1 $2");
		return dao.findByCatnameContainsIgnoreCase(clean);
	}

	@Override
	public Message add(SubCategory subcat) {
		Message m=new Message();
		try {
		   dao.save(subcat);
		   m.setS("Success");
		}catch(Exception ex) {
			m.setS("Error:"+ex);
		}
		return m;
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
	public SubCategory find(int id) {
		SubCategory s = new SubCategory();
		Optional<SubCategory> eop=dao.findById(id);
		if(eop.isPresent()) {
			s=eop.get();
		}
		else {
			System.out.println("findById: item not found");
		}
		return s;
	}

	@Override
	public List<SubCategory> findAll() {
		return dao.findAll();
	}

}
