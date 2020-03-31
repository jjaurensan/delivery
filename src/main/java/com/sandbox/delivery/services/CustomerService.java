package com.sandbox.delivery.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandbox.delivery.entities.Customer;
import com.sandbox.delivery.repositories.CustomerRepository;
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Transactional
	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public List <Customer> getAllCustomer(){
		return (List<Customer>) customerRepository.findAll();
	}

	public Customer getByCustomerNumber(String customerNumber) {
		return customerRepository.findByCustomerNumber(customerNumber);
	}
	
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);		
	}
	@Transactional
	public void deleteCustomerById(long idCustomer) {
		customerRepository.deleteById(idCustomer);
		
	}
	@Transactional
	public void deleteCustomerByCustomerNumber(String customerNumber) {
		customerRepository.deleteByCustomerNumber(customerNumber);
		
	}
}
