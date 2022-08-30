package com.westbarn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.westbarn.model.SubCategory;

@Repository
public interface SubCategoryDao extends JpaRepository<SubCategory,Integer>{

	public List<SubCategory> findByCatnameContainsIgnoreCase(String name);
}
