package com.sandbox.delivery.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sandbox.delivery.mapper.AddressMapper;
import com.sandbox.delivery.persistent.entities.Address;
import com.sandbox.delivery.persistent.repositories.AddressRepository;
import com.sandbox.delivery.services.bo.AddressBO;
import com.sandbox.delivery.services.impl.AddressServiceImpl;

@SpringBootTest
public class AddressServiceTest {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private AddressServiceImpl addressService;

	private AddressBO addressBO;

	@BeforeEach
	void beforeEach() {
		addressBO = addressService.create(new AddressBO("Rue 1", "Rue 2", "Rue3", "33300", "Bordeaux", false));
	}

	@AfterEach
	void afterEach() {
		
		addressRepository.delete(AddressMapper.INSTANCE.addressBOToAddress(addressBO));
	}

	@Test
	void createAddress_CreateANewAddressInRepository() throws Exception {

		Optional<Address> resultQuery = addressRepository.findById(addressBO.getIdAddress());
		assertTrue(resultQuery.isPresent());
	}

	@Test
	void updateAdress_UpdateAAddessInRepo() throws Exception {

		addressBO.setCity("louens");
		addressService.update(addressBO);
		Address resultQuery = addressRepository.findById(addressBO.getIdAddress()).get();
		assertTrue(resultQuery.getCity().equals("louens"));
	}

}
