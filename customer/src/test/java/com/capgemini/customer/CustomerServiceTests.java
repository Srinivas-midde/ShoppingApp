package com.capgemini.customer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.customer.entity.Customer;
import com.capgemini.customer.repository.CustomerRepository;
import com.capgemini.customer.service.impl.CustomerServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTests {

MockMvc mockmvc;
	
	@Mock
	public CustomerRepository repository;
	
	@InjectMocks
	private CustomerServiceImpl service;
	
	Customer customer;
	
	@Before
	public void setUP() {
		MockitoAnnotations.initMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(service).build();
		customer = new Customer(123, "Sri", "xyz@cvf.com", "test");
	}
	
	@Test
	public void testAddcustomer() throws Exception {
		when(repository.save(Mockito.isA(Customer.class))).thenReturn(customer);
		Customer test = service.addCustomer(customer);
		assertEquals(customer, test);
	}
	
	@Test
	public void testUpdate() {
		Customer test = new Customer();
		when(repository.save(Mockito.isA(Customer.class))).thenReturn(customer);
		Customer temp =service.updateCustomer(test);
		assertEquals(customer, temp);
	}
	
	@Test
	public void findByIdTest() throws Exception {
		Optional<Customer> optionalCustomer = Optional.of(customer);
		when(repository.findById(Mockito.isA(Integer.class))).thenReturn(optionalCustomer);
		assertEquals(optionalCustomer.get(), service.findById(123));
	}
	
	@Test
	public void deleteTest() throws Exception {
		service.deleteCustomer(customer);
		verify(repository).delete(customer);
	}
	

}
