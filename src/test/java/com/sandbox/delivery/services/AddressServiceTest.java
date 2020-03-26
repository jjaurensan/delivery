package com.sandbox.delivery.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sandbox.delivery.entities.Address;
import com.sandbox.delivery.repositories.AddressRepository;

@SpringBootTest
public class AddressServiceTest {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private AddressService addressService;

	private Address address;

	@BeforeEach
	void beforeEach() {
		address = addressService.create(new Address("Rue 1", "Rue 2", "Rue3", "33300", "Bordeaux", false));
	}

	@AfterEach
	void afterEach() {
		addressRepository.delete(address);
	}

	@Test
	void createAddress_CreateANewAddressInRepository() throws Exception {

		Optional<Address> resultQuery = addressRepository.findById(address.getIdAddress());
		assertTrue(resultQuery.isPresent());
	}

	@Test
	void updateAdress_UpdateAAddessInRepo() throws Exception {

		address.setCity("louens");
		addressService.update(address);
		Address resultQuery = addressRepository.findById(address.getIdAddress()).get();
		assertTrue(resultQuery.getCity().equals("louens"));
	}

}
