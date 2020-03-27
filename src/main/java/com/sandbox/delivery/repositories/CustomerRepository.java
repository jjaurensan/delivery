package com.sandbox.delivery.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sandbox.delivery.entities.Customer;
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Customer findByCustomerNumber(String customerNumber);

	void deleteByCustomerNumber(String customerNumber);

}
