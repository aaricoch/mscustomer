package com.nttd.mscustomer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.nttd.mscustomer.dto.CustomerDto;
import com.nttd.mscustomer.dto.ResponseDto;
import com.nttd.mscustomer.entity.Customer;
import com.nttd.mscustomer.repository.CustomerRepository;
import com.nttd.mscustomer.service.CustomerService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CustomerServiceImpl implements CustomerService {

	@Inject
	private CustomerRepository customerRepository;
	
	@ConfigProperty(name  = "excepcion.003")
	String excepcion003;

	@ConfigProperty(name  = "excepcion.002")
	String excepcion002;
	
	
	@ConfigProperty(name  = "excepcion.004")
	String excepcion004;
	
	@ConfigProperty(name  = "excepcion.005")
	String excepcion005;

	@ConfigProperty(name  = "error.generic")
	String errorgeneric;

	@ConfigProperty(name  = "state.valor.activo")
	String stateActivo;

	@ConfigProperty(name  = "state.valor.inactivo")
	String stateInactivo;

	

	@Transactional
	public ResponseDto saveCustomer(CustomerDto customerDto) {
		try {
			// VERIFICAR SI ES NUEVO O YA EXISTENTE
				Customer customer =    customerRepository.find("numberDocument", customerDto.getNumberDocument()).firstResult();
				if(customer == null){
					customer = new Customer();
					customer.setTypeCustomer(customerDto.getTypeCustomer());
					customer.setNumberDocument(customerDto.getNumberDocument());
					customer.setName(customerDto.getName());
					customer.setLastname(customerDto.getLastname());
					customer.setState(stateActivo);
					customerRepository.persist(customer);
					return new ResponseDto(201, excepcion003,customer);
				}				
			return new ResponseDto(200, excepcion003,customer);
		} catch (Exception ex) {
			return new ResponseDto(400, errorgeneric, ex.getMessage());
		}
	}

	@Transactional
	public ResponseDto updateCustomer(Long id,CustomerDto customerDto) {
		try {
			Customer customer = customerRepository.findById(id);
			if(customer == null)
				return new ResponseDto(204, excepcion002,"");						
			customer.setName(customerDto.getName());
			customer.setLastname(customerDto.getLastname());
			customerRepository.persist(customer);
			return new ResponseDto(200, excepcion004,customer);
		} catch (Exception ex) {
			return new ResponseDto(400, errorgeneric, ex.getMessage());
		}
	}

	@Override
	public ResponseDto getAllCustomer(CustomerDto customerDto) {
	
		try{
			Map<String, Object> params = new HashMap<>();
			params.put("typeCustomer", customerDto.getTypeCustomer());
			params.put("numberDocument", customerDto.getNumberDocument());
            List<Customer> listcustomer = customerRepository.find
							("typeCustomer = :typeCustomer and numberDocument = :numberDocument",
							 params).list();
            if(listcustomer.size() == 0)
                return  new ResponseDto(204,excepcion002,"");
            else return  new ResponseDto(200,excepcion003,listcustomer.get(0));
            
        }catch(Exception ex){
            return  new ResponseDto(400,errorgeneric,ex.getMessage());
        } 



	}
		
	@Override
	public ResponseDto getCustomerById(Long id) {
		try{
			Customer customer = customerRepository.findById(id);
            if(customer == null)
                return  new ResponseDto(204,excepcion002,"");
            else return  new ResponseDto(200,excepcion003,customer);
            
        }catch(Exception ex){
            return  new ResponseDto(400,errorgeneric,ex.getMessage());
        } 

	}

	@Transactional
	public ResponseDto deleteCustomer(Long id) {
		try{
            Customer customer = customerRepository.findById(id);
            if(customer == null)
                return  new ResponseDto(204,excepcion002,"");
            else{
                customer.setState(stateInactivo);
                customerRepository.persist(customer);
                return  new ResponseDto(200,excepcion005,customer);
            } 
        }catch(Exception ex){
            return  new ResponseDto(400,errorgeneric,ex.getMessage());
        } 

	}

}
