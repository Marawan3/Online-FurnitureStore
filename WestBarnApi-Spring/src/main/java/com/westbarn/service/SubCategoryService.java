package com.westbarn.service;

import java.util.List;

import com.westbarn.model.Message;
import com.westbarn.model.SubCategory;

public interface SubCategoryService {
	public List<SubCategory> findUnderCategory(String catName);
	public Message add(SubCategory subcat);
	public Message delete(int id);
	public SubCategory find(int id);
	public List<SubCategory> findAll();
}
