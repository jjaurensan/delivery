package com.sandbox.delivery.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sandbox.delivery.mapper.CustomerMapper;
import com.sandbox.delivery.persistent.entities.Customer;
import com.sandbox.delivery.persistent.repositories.CustomerRepository;
import com.sandbox.delivery.services.bo.AddressBO;
import com.sandbox.delivery.services.bo.CustomerBO;
import com.sandbox.delivery.services.impl.CustomerServiceImpl;

@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerServiceImpl customerService;

	private CustomerBO customerBO;
	private List<AddressBO> addressListBO = new ArrayList<>();

	@BeforeEach
	void beforeEach() {
		
		addressListBO.add(new AddressBO("Rue mon desir", null, null, "33290", "Louens", false));
		customerBO = customerService.create(new CustomerBO("55JJA", addressListBO, "0558567272", "john doe", false));
	}

	@AfterEach
	void afterEach() { 
		customerRepository.delete(CustomerMapper.INSTANCE.customerBOToCustomer(customerBO));

	}

	@Test
	void getCustomerByCustomerNumber() throws Exception {
		Customer resultQuery = customerRepository.findByCustomerNumber(customerBO.getCustomerNumber());
		CustomerBO result = CustomerMapper.INSTANCE.customerToCustomerBO(resultQuery);
		assertEquals(customerBO.getCustomerNumber(), result.getCustomerNumber());
	}

	@Test
	void getListAdressOfCustomer() throws Exception {
		Customer resultQuery = customerRepository.findById(customerBO.getIdCustomer()).get();
		System.out.println("resultQuery : " + resultQuery.getCustomerListDeliveryAddress().size());
		CustomerBO result = CustomerMapper.INSTANCE.customerToCustomerBO(resultQuery);
		assertFalse(result.getCustomerListDeliveryAddress().isEmpty());
		//assertTrue(customerBO.getCustomerListDeliveryAddressBO().containsAll(addressListBO));
	}

}
