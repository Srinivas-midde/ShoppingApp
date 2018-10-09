package com.capgemini.order.service;

import com.capgemini.order.entities.Order;
import com.capgemini.order.exception.OrderNotFoundException;

public interface OrderService {
	
	public Order findOrderbyId(int orderId) throws OrderNotFoundException;
	public Order addOrder(Order order);
	public void removeOrder(Order order);
	public Order editOrder(Order order);
	//public Order findOrderbyName(int orderquantity);

}
