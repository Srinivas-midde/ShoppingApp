package com.capgemini.order.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.capgemini.order.entities.Order;
import com.capgemini.order.exception.OrderNotFoundException;
import com.capgemini.order.repository.OrderRepository;
import com.capgemini.order.service.OrderService;

public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order findOrderbyId(int orderId) throws OrderNotFoundException {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(optionalOrder.isPresent())
			return optionalOrder.get();
		throw new OrderNotFoundException("Order does not exists");
	}

	@Override
	public Order addOrder(Order order) {
		return null;
	}

	@Override
	public void removeOrder(Order order) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order editOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public Order findOrderbyName(int orderquantity) {
		Optional<Order> optionalOrder = orderRepository.findbyOrderquantity(orderquantity);
		if(optionalOrder.isPresent())
			return optionalOrder.get();
		throw new OrderNotFoundException("Order does not exists");
	}*/
	
	

}
