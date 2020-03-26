package com.sandbox.delivery.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sandbox.delivery.entities.Address;
import com.sandbox.delivery.entities.Customer;
import com.sandbox.delivery.repositories.CustomerRepository;

@SpringBootTest
public class CustomerServiceTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerService customerService;

	private Customer customer;
	private List<Address> addressList = new ArrayList<>();

	@BeforeEach
	void beforeEach() {
		addressList.add(new Address("Rue mon desir", null, null, "33290", "Louens", false));
		customer = customerService.create(new Customer("55JJA", addressList, "0558567272", "john doe", false));
	}

	@AfterEach
	void afterEach() {
		customerRepository.delete(customer);

	}

	@Test
	void getCustomerByCustomerNumber() throws Exception {
		Customer resultQuery = customerRepository.findByCustomerNumber(customer.getCustomerNumber());
		assertEquals(customer, resultQuery);
	}

	@Test
	void getListAdressOfCustomer() throws Exception {
//		List<Address> addressList = new ArrayList<>();
//		addressList.add(new Address("Rue mon desir 2", "nid du moustique", null, "33290", "Louens", false));
//		Customer customer = customerService.create(new Customer("44AURES", addressList, "0558567272", "john doe", false));
		Customer resultQuery = customerRepository.findById(customer.getIdCustomer()).get();
		assertFalse(resultQuery.getCustomerListDeliveryAddress().isEmpty());
		assertTrue(customer.getCustomerListDeliveryAddress().containsAll(addressList));
	}

}
