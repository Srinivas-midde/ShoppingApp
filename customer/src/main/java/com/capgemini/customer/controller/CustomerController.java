package com.capgemini.customer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.customer.entity.Customer;
import com.capgemini.customer.exception.CustomerNotFoundException;
import com.capgemini.customer.service.CustomerService;

@RestController
public class CustomerController {
	
	private final Logger log = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerService customerService;
	
	@PostMapping("/customer")
	public ResponseEntity<Customer> addProduct(@RequestBody Customer customer) {
		ResponseEntity<Customer> responseEntity = new ResponseEntity<Customer>(customerService.addCustomer(customer),
				HttpStatus.OK);
		log.info("Customer added");
		return responseEntity;
	}
	
	@PutMapping("/customer")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		try {
			customerService.findById(customer.getCustomerId());
			log.info("Customer Updating");
			return new ResponseEntity<Customer>(customerService.updateCustomer(customer), HttpStatus.OK);
		} catch (CustomerNotFoundException exception) {
			// logged the exception
		}
		log.info("Customer not updated");
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> findById(@PathVariable int customerId) {
		try {
			Customer customerFromDb = customerService.findById(customerId);
			log.info("Finding Customer");
			return new ResponseEntity<Customer>(customerFromDb, HttpStatus.OK);
		} catch (CustomerNotFoundException exception) {
			// logged the exception
		}
		log.info("Customer not found");
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable int customerId) {
		try {
			Customer customerFromDb = customerService.findById(customerId);
			/*if (customerFromDb != null) {*/
				customerService.deleteCustomer(customerFromDb);
				log.info("Customer deleted");
				return new ResponseEntity<Customer>(HttpStatus.OK);
			/*}*/
		} catch (CustomerNotFoundException exception) {
			// logged the exception
		}
		log.info("Customer not deleted");
		return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
	}

}
