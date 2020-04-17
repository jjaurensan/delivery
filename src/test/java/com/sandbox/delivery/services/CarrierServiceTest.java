package com.sandbox.delivery.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sandbox.delivery.entities.Carrier;
import com.sandbox.delivery.repositories.CarrierRepository;

@SpringBootTest
public class CarrierServiceTest {
	
		@Autowired
		private CarrierRepository carrierRepository;
		
		@Autowired
		private CarrierService carrierService;
		
		private Carrier carrier;
		private Carrier  carrierDb;
		@BeforeEach
		void beforeEach() {
			carrierDb=carrierRepository.save(new Carrier("Cmainan", "rue 1", "Rue 2", "33320", "Pessac", "0556587272"));
			carrier = new Carrier("youhou", "rue 1", "Rue 2", "33366", "lol", "0556587272");

		}

		@AfterEach
		void afterEach() {
			carrierRepository.delete(carrier);
		}
		
		@Test
		void create_CreateNewCarrierInDatabase() throws Exception {
			carrierService.create(carrier);			
			Optional<Carrier> result = carrierRepository.findById(carrier.getIdCarrier());
			assertTrue(result.isPresent());
		}
		
		@Test
		void update_UpdateCarrierExistingInDatabase() throws Exception {
			Carrier carrierUpdate= carrierService.find(carrierDb);
			if (carrierUpdate != null) {
				carrierUpdate.setName("NewName");
				carrierService.update(carrierUpdate);
				}
				assertTrue(!carrierUpdate.equals(carrier));						
		}
}
