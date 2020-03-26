package com.sandbox.delivery.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sandbox.delivery.entities.Address;
import com.sandbox.delivery.entities.Carrier;
import com.sandbox.delivery.entities.Customer;
import com.sandbox.delivery.entities.Delivery;
import com.sandbox.delivery.repositories.DeliveryRepository;

@SpringBootTest
public class DeliveryServiceTest {

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Autowired
	private DeliveryService deliveryService;

	private Carrier carrier;
	private Customer customer;
	private List<Address> addressList = new ArrayList<>();
	private Delivery delivery;
	
	@BeforeEach
	void beforeEach() {
		carrier = new Carrier("Cmainan", "rue 1", "Rue 2", "33320", "Pessac", "0556587272");
		addressList.add(new Address("Rue 1", null, null, "33300", "Bordeaux", false));
		customer = new Customer("335AURES", addressList, "0558567272", "john doe", false);
	}

	@AfterEach
	void afterEach() {
		deliveryRepository.delete(delivery);
	}

	@Test
	void createDelivery_CreateNewDeliveryInDatabase() throws Exception {		
		delivery = deliveryService.create(new Delivery(carrier, customer,2));
		Optional<Delivery> result = deliveryRepository.findById(delivery.getIdDelivery());
		assertTrue(result.isPresent());
	}
	
	
}
