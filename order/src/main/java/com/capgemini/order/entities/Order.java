package com.capgemini.order.entities;

public class Order {
	
	private int orderId;
	private int orderquantity;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int orderId, int orderquantity) {
		super();
		this.orderId = orderId;
		this.orderquantity = orderquantity;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getOrderquantity() {
		return orderquantity;
	}
	public void setOrderquantity(int orderquantity) {
		this.orderquantity = orderquantity;
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderquantity=" + orderquantity + "]";
	}
	
}
