package com.westbarn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.westbarn.model.CartItem;
import com.westbarn.model.Customer;

@Repository
public interface CartDao extends JpaRepository<CartItem,Integer>{
	public List<CartItem> findByUser(Customer c);
}
