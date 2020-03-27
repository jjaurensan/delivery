package com.sandbox.delivery.controller.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sandbox.delivery.entities.Customer;
import com.sandbox.delivery.exception.CustomerExistExeception;
import com.sandbox.delivery.exception.CustomerNoExistExeception;
import com.sandbox.delivery.services.CustomerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

	@GetMapping(path = "/customers")
	public List<Customer> getListOfCustomers() {
		return customerService.getAllCustomer();
	}

	@GetMapping(path = "/customer/{customerNumber}")
	public Customer getCustomerByCustomerNumber(@PathVariable String customerNumber) {
		return customerService.getByCustomerNumber(customerNumber);
	}
	
	@PostMapping(path = "/customer/")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer,
			UriComponentsBuilder uriBuilder)
	throws CustomerExistExeception,CustomerNoExistExeception {
		Customer customerCreate = this.customerService.create(customer);
		URI uri = uriBuilder.path("/customer/{customerNumber}")
				.buildAndExpand(customerCreate.getCustomerNumber()).toUri();
		return ResponseEntity.created(uri).body(customerCreate);
	}

	@PutMapping(path = "/customer/{customerNumber}")
	public ResponseEntity<Customer>updateCustomer(@RequestBody Customer customer,
			UriComponentsBuilder uriBuilder){
		Customer customerUpdate = this.customerService.updateCustomer(customer);
		URI uri = uriBuilder.path("/customer/{customerNumber}").buildAndExpand(customerUpdate.getCustomerNumber()).toUri();
		return ResponseEntity.created(uri).body(customerUpdate);
	}
	
	@DeleteMapping(path = "/customer/{customerNumber}")
	public void deleteCustomerByCustomNumber(@PathVariable String customerNumber) {
		this.customerService.deleteCustomerByCustomerNumber(customerNumber);
	}
}
