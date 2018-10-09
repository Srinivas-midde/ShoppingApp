package com.capgemini.customer.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.customer.entity.Customer;
import com.capgemini.customer.exception.CustomerNotFoundException;
import com.capgemini.customer.repository.CustomerRepository;
import com.capgemini.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(Customer customer) {
			customerRepository.delete(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer findById(int customerId) throws CustomerNotFoundException {
		Optional<Customer> optionalProduct = customerRepository.findById(customerId);
		if(optionalProduct.isPresent())
			return optionalProduct.get();
		throw new CustomerNotFoundException("Customer does not exists");
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

}
