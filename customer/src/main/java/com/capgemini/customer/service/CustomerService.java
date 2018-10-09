package com.capgemini.customer.service;

import java.util.List;

import com.capgemini.customer.entity.Customer;
import com.capgemini.customer.exception.CustomerNotFoundException;

public interface CustomerService {
	
	public Customer addCustomer(Customer customer);
	public void deleteCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public List<Customer> findAll();
	public Customer findById(int customerId) throws CustomerNotFoundException;

}
