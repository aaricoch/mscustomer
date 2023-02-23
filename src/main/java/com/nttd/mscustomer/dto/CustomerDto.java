package com.nttd.mscustomer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
	
	private Long idCustomer;
	private String typeCustomer;
	private String numberDocument;
	private String name;
	private String lastname;
	
	public CustomerDto() {
		
	}
}
