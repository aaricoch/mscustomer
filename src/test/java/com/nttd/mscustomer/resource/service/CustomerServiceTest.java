package com.nttd.mscustomer.resource.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.nttd.mscustomer.dto.CustomerDto;
import com.nttd.mscustomer.dto.ResponseDto;
import com.nttd.mscustomer.entity.Customer;
import com.nttd.mscustomer.repository.CustomerRepository;
import com.nttd.mscustomer.service.CustomerService;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;

import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
public class CustomerServiceTest {
	
	@Inject
	CustomerService customerService;
	
	@InjectMock
	CustomerRepository customerRepository;
	
	
	@Test
	public void saveCustomer1() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setCode(201);
		Mockito.doNothing().when(customerRepository).persist(any(Customer.class));
		ResponseDto response = customerService.saveCustomer(new CustomerDto());
		Assertions.assertEquals(201, response.getCode());
	}
	
	
	@Test
	public void updateCustomer1() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setCode(201);
		
		Customer customerObject = new Customer();
		Mockito.when(customerRepository.findById(any(Long.class))).thenReturn(customerObject);
		
		Mockito.doNothing().when(customerRepository).persist(any(Customer.class));
		ResponseDto response = customerService.saveCustomer(new CustomerDto());
		Assertions.assertEquals(201, response.getCode());
	}
	
	
	@Test
	public void getByIdTest() {
		Customer customerObject = new Customer();
		customerObject.setIdCustomer(1L);
		Mockito.when(customerRepository.findById(any(Long.class))).thenReturn(customerObject);
		//Customer customer = customerService.getById((any(Long.class)));
		//Assertions.assertEquals(customerObject.getIdCustomer(),customer.getIdCustomer());
		
	}
	
	@Test
	public void deleteCustomer1() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setCode(201);
		
		Customer customerObject = new Customer();
		Mockito.when(customerRepository.findById(any(Long.class))).thenReturn(customerObject);
		Mockito.doNothing().when(customerRepository).persist(customerObject);
		ResponseDto response = customerService.deleteCustomer(any(Long.class));
		Assertions.assertEquals(201, response.getCode());
	}
	
}
