package com.nttd.mscustomer.resource;


import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;

import com.nttd.mscustomer.dto.CustomerDto;
import com.nttd.mscustomer.dto.ResponseDto;
import com.nttd.mscustomer.service.CustomerService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

	
	@Inject
	CustomerService customerService;

	@Inject
	Logger logger;
	
	
	
	@ConfigProperty(name  = "excepcion.006")
	String excepcion006;


	@ConfigProperty(name  = "mensaje.http.422")
	String excepcion422;

	
	
	/**
	 *  REGISTRAR CLIENTE
	 * */
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Fallback(fallbackMethod = "addCustomerDefault")
	@Operation(summary = "Registrar el cliente",description = "permite Registrar los clientes.")
	public Response addCustomer(CustomerDto customer) {
		 logger.info("Inicio CustomerResource.addCustomer");
		ResponseDto response = customerService.saveCustomer(customer);
		return Response.ok(response).status(response.getCode()).build();
	}
	
	public Response addCustomerDefault(CustomerDto customer) {
		 logger.info("Inicio CustomerResource.addCustomerDefault");
		ResponseDto response = new ResponseDto(422,excepcion422,excepcion006+" - "+customer.getNumberDocument());
		return Response.ok(response).status(response.getCode()).build();
	}

	
	/**
	 *  ACTUALIZAR CLIENTE
	 * */
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(summary = "Actualizar el cliente",description = "permite Actualizar los clientes.")
	public Response updateCustomer(@PathParam(value = "id") Long id, CustomerDto customerDto) {
		logger.info("Inicio CustomerResource.updateCustomer");
		ResponseDto response  =customerService.updateCustomer(id,customerDto);
		return Response.ok(response).status(response.getCode()).build();
	}

	/**
	 *  OBTENER CLIENTE por filtro
	 * */
	@GET
	@Operation(summary = "Obtienes el cliente",description = "Obtienes el clientes por filtros.")
	public Response getAllCustomer(CustomerDto customerDto) {
		logger.info("Inicio CustomerResource.getAllCustomer");
		ResponseDto response = customerService.getAllCustomer(customerDto);							
		return Response.ok(response).status(response.getCode()).build();
	}



	/**
	 *  OBTENER CLIENTE
	 * */
	@GET
	@Path("{id}")
	@Timeout(700)
	@Operation(summary = "Obtienes el cliente por Id",description = "Obtienes el clientes por Id.")
	public Response getCustomerById(@PathParam(value = "id") Long id) {
		logger.info("Inicio CustomerResource.getCustomerById");
		ResponseDto response = customerService.getCustomerById(id);
		return Response.ok(response).status(response.getCode()).build();
	}

	
	/**
	 *  ELIMINAR CLIENTE
	 * */
	@DELETE
	@Path("{id}")
	@Operation(summary = "Eliminar el cliente",description = "Eliminas el clientes por Id.")
	public Response deleteCustomer(@PathParam(value = "id") Long id) {
		logger.info("Inicio CustomerResource.deleteCustomer");
		ResponseDto response =customerService.deleteCustomer(id);
		return Response.ok(response).status(response.getCode()).build();
	}
	
	
	 

}
