package com.nttd.mscustomer.resource;


import static org.mockito.ArgumentMatchers.any;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.nttd.mscustomer.dto.CustomerDto;
import com.nttd.mscustomer.dto.ResponseDto;
import com.nttd.mscustomer.entity.Customer;
import com.nttd.mscustomer.service.CustomerService;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;

@QuarkusTest
public class CustomerResourceTest {
	
	@Inject
	CustomerResource customerResource ;
	
	@InjectMock
	CustomerService customerService; 
	
	private static Logger logger;
	
	@BeforeAll
	public static void setup() {
		logger = Mockito.mock(Logger.class);
		
		Mockito.doNothing().when(logger).info(any(Object.class));
	}
	
	
	@Test
	public void addCustomer1() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setCode(201);
		responseDto.setMessage("Se registro correctamente.");
		
		Mockito.when(customerService.saveCustomer(Mockito.any(CustomerDto.class)))
			.thenReturn(responseDto);
		
		ResponseDto response =  (ResponseDto) customerResource.addCustomer(new CustomerDto()).getEntity();
		
		Assertions.assertEquals(201, response.getCode());
	
	
	}
	
	/**
	 * test cuando el id a actualizar no existe en la base de datos 
	 */
	@Test
	public void updateCustomer1() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setCode(404);
		
		//Customer customerNotFound = null;
		//Mockito.when(customerService.getById(any(Long.class))).thenReturn(customerNotFound);
		
		ResponseDto response =  (ResponseDto) customerResource.updateCustomer(1l,new CustomerDto()).getEntity();
		
		Assertions.assertEquals(404, response.getCode());
	
	}
	
	/**
	 * actualizacion flujo normal
	 */
	@Test
	public void updateCustomer2() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setCode(201);
		
		Customer customerFound = new Customer();
		customerFound.setIdCustomer(1l);
		/*Mockito.when(customerService.getById(any(Long.class))).thenReturn(customerFound);
		
		Mockito.when(customerService.updateCustomer(Mockito.any(CustomerDto.class)))
		.thenReturn(responseDto);*/
		ResponseDto response =  (ResponseDto) customerResource.updateCustomer(customerFound.getIdCustomer(),
				new CustomerDto()).getEntity();
		
		Assertions.assertEquals(201, response.getCode());
	
	}

}
