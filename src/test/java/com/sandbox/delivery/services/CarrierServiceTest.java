package com.sandbox.delivery.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sandbox.delivery.mapper.CarrierMapper;
import com.sandbox.delivery.persistent.entities.Carrier;
import com.sandbox.delivery.persistent.repositories.CarrierRepository;
import com.sandbox.delivery.services.bo.CarrierBO;
import com.sandbox.delivery.services.impl.CarrierServiceImpl;

@SpringBootTest
public class CarrierServiceTest {
	
		@Autowired
		private CarrierRepository carrierRepository;
		
		@Autowired
		private CarrierServiceImpl carrierService;
		
		private CarrierBO carrier;
		private Carrier  carrierDb;
		@BeforeEach
		void beforeEach() {
			carrierDb=carrierRepository.save(new Carrier("Cmainan", "rue 1", "Rue 2", "33320", "Pessac", "0556587272"));
			carrier = new CarrierBO("youhou", "rue 1", "Rue 2", "33366", "lol", "0556587272");

		}

		@AfterEach
		void afterEach() {
			carrierRepository.delete(CarrierMapper.INSTANCE.carrierBOToCarrier(carrier));
		}
		
		@Test
		void create_CreateNewCarrierInDatabase() throws Exception {
			CarrierBO carrierBO = carrierService.create(carrier);			
			Optional<Carrier> result = carrierRepository.findById(carrierBO.getIdCarrier());
			assertTrue(result.isPresent());
		}
		
		@Test
		void update_UpdateCarrierExistingInDatabase() throws Exception {
			
			CarrierBO carrierUpdate= carrierService.find(
					CarrierMapper.INSTANCE.carrierToCarrierBO(carrierDb)
					);
			
			if (carrierUpdate != null) {
				carrierUpdate.setName("NewName");
				carrierService.update(carrierUpdate);
				}
				assertTrue(!carrierUpdate.equals(carrier));						
		}
}
