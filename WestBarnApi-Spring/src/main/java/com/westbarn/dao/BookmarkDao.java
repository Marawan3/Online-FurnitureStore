package com.westbarn.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.westbarn.model.Bookmark;
import com.westbarn.model.Customer;

@Repository
public interface BookmarkDao extends JpaRepository<Bookmark,Integer>{
	public List<Bookmark> findByUser(Customer c);
}
