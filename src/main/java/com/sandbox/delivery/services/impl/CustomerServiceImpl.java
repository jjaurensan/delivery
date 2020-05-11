package com.sandbox.delivery.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandbox.delivery.mapper.CustomerMapper;
import com.sandbox.delivery.persistent.entities.Customer;
import com.sandbox.delivery.persistent.repositories.CustomerRepository;
import com.sandbox.delivery.services.CustomerService;
import com.sandbox.delivery.services.bo.CustomerBO;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Transactional
	public CustomerBO create(CustomerBO customerBO) {
		Customer c = CustomerMapper.INSTANCE.customerBOToCustomer(customerBO);
		customerBO = CustomerMapper.INSTANCE.customerToCustomerBO(customerRepository.save(c));
		return customerBO;
	}
	
	public List <CustomerBO> getAllCustomer(){		
		return CustomerMapper
				.INSTANCE
				.listCustomerToListCustomerBO((List<Customer>) customerRepository.findAll()) ;
	}

	public CustomerBO getByCustomerNumber(String customerNumber) {
		return CustomerMapper
				.INSTANCE
				.customerToCustomerBO(customerRepository.findByCustomerNumber(customerNumber));
	}
	
	@Transactional
	public CustomerBO updateCustomer(CustomerBO customerBO) {
		return create(customerBO);		
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
