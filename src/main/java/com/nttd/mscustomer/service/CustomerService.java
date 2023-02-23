package com.nttd.mscustomer.service;


import com.nttd.mscustomer.dto.CustomerDto;
import com.nttd.mscustomer.dto.ResponseDto;

public interface CustomerService {
	
	public ResponseDto saveCustomer(CustomerDto customerDto) ;
	public ResponseDto updateCustomer(Long id,CustomerDto customerDto);
	public ResponseDto getAllCustomer(CustomerDto customerDto);
	public ResponseDto getCustomerById(Long id) ;
	public ResponseDto deleteCustomer(Long id);
}
