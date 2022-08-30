package com.westbarn.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.westbarn.model.UserOrder;


@Repository
public interface OrderDao extends JpaRepository<UserOrder,Integer>{

}
