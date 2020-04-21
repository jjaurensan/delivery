package com.sandbox.delivery.persistent.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sandbox.delivery.persistent.entities.Customer;
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Customer findByCustomerNumber(String customerNumber);
	
	void deleteByCustomerNumber(String customerNumber);


}
