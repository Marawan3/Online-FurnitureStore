package com.westbarn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.westbarn.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product,Integer>{

	public List<Product> findByProductnameContainsIgnoreCase(String name);
}
