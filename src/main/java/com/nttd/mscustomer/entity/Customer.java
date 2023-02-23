package com.nttd.mscustomer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "BQMCUSTOMER")
public class Customer {

	@Id
	@SequenceGenerator(
            name = "customerSequence",
            sequenceName = "customer_id_seq",
            allocationSize = 1,
            initialValue = 9)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customerSequence") 
	private Long idCustomer;
	private String typeCustomer;
	private String numberDocument;
	private String name;
	private String lastname;
	private String state;

}
