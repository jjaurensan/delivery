package com.sandbox.delivery.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandbox.delivery.entities.Customer;
import com.sandbox.delivery.repositories.CustomerRepository;
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	
	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public List <Customer> getAll(){
		return (List<Customer>) customerRepository.findAll();
	}

	public Customer getByCustomerNumber(String customerNumber) {
		return customerRepository.findByCustomerNumber(customerNumber);
	}

}
