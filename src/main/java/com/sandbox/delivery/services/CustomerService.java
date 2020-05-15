package com.sandbox.delivery.services;

import java.util.List;

import com.sandbox.delivery.services.bo.CustomerBO;

public interface CustomerService {
	public CustomerBO create(CustomerBO customerBO);

	public List<CustomerBO> getAllCustomer();

	public CustomerBO getByCustomerNumber(String customerNumber);

	public CustomerBO updateCustomer(CustomerBO customerBO);

	public void deleteCustomerById(long idCustomer);

	public void deleteCustomerByCustomerNumber(String customerNumber);

	public void deleteAll();

}
