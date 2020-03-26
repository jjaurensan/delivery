package com.sandbox.delivery.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sandbox.delivery.entities.Customer;
import com.sandbox.delivery.services.CustomerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CustomerRestController {

	@Autowired
	private CustomerService customerService;

	@GetMapping(path = "/customers")
	public List<Customer> getListOfCustomers() {
		return customerService.getAll();
	}

	@GetMapping(path = "/customer/{customerNumber}")
	public Customer getCustomerByCustomerNumber(@PathVariable String customerNumber) {
		return customerService.getByCustomerNumber(customerNumber);
	}

}
