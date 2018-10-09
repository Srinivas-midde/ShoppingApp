package com.capgemini.customer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.customer.controller.CustomerController;
import com.capgemini.customer.entity.Customer;
import com.capgemini.customer.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerControllerTests {
	
	MockMvc mockmvc;
	
	@Mock
	public CustomerService service;
	
	@InjectMocks
	private CustomerController customercontroller;
	
	Customer customer;
	
	@Before
	public void setUP() {
		
		MockitoAnnotations.initMocks(this);
		mockmvc = MockMvcBuilders.standaloneSetup(customercontroller).build();
		customer = new Customer(123, "Sri", "xyz@cvf.com", "test");

	}

	
	@Test
	public void testAddProduct() throws Exception {

		when(service.addCustomer(Mockito.isA(Customer.class))).thenReturn(customer);
		
		mockmvc.perform(post("/customer").
					 contentType(MediaType.APPLICATION_JSON_UTF8)
					.content("{\"customerId\": 123, \"customerName\": \"Sri\", \"customerEmail\": \"x@f.com\", \"Password\": \"test\"}")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.customerId").value(123))
				.andDo(print());
				
	}
	
	
	@Test
	public void testupdateCustomer() throws Exception {
		when(service.updateCustomer(Mockito.isA(Customer.class))).thenReturn(customer);
		
		mockmvc.perform(put("/customer").
				contentType(MediaType.APPLICATION_JSON_UTF8)
				.content("{\"customerId\": 123, \"customerName\": \"Sri\", \"customerEmail\": \"x@f.com\", \"Password\": \"test\"}")
				.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(jsonPath("$.customerName").value("Sri"))
		.andExpect(status().isOk());
			
	}
	
	@Test
	public void testFindProductById() throws Exception {
		when(service.findById(123)).thenReturn(customer);
		
		mockmvc.perform(get("/customers/123").accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(jsonPath("$.customerName").value("Sri"))
						.andExpect(status().isOk());
	}
	
	@Test
	public void testDelete() throws Exception {
		
		Customer customer = null;
	
		when(service.findById(123))
					.thenReturn(customer);
		
		mockmvc.perform(delete("/customers/123").accept(MediaType.APPLICATION_JSON))
						.andDo(print())
						.andExpect(status().isOk());
		verify(service).deleteCustomer(customer);
		
	}

	
	
}
