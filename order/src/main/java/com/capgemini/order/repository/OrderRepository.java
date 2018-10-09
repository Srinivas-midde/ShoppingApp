package com.capgemini.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.order.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	/*@Query("{'orderquantity': ?0}")
	public List<Order> findbyOrder*/

}
