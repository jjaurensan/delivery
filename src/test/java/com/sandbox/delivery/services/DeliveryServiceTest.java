package com.sandbox.delivery.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sandbox.delivery.mapper.DeliveryMapper;
import com.sandbox.delivery.persistent.entities.Delivery;
import com.sandbox.delivery.persistent.repositories.DeliveryRepository;
import com.sandbox.delivery.services.bo.AddressBO;
import com.sandbox.delivery.services.bo.CarrierBO;
import com.sandbox.delivery.services.bo.CustomerBO;
import com.sandbox.delivery.services.bo.DeliveryBO;

@SpringBootTest
public class DeliveryServiceTest {

	final Logger logger = LoggerFactory.getLogger(DeliveryServiceTest.class);
	
	
	@Autowired
	private DeliveryRepository deliveryRepository;

	@Autowired
	private DeliveryService deliveryService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CarrierService carrierService;

	private CarrierBO carrier;
	private CustomerBO customer;
	private List<AddressBO> addressList = new ArrayList<>();
	private DeliveryBO deliveryBO;

	@BeforeEach
	void beforeEach() {
		carrier = new CarrierBO("Cmainan", "rue 1", "Rue 2", "33320", "Pessac", "0556587272");
		addressList.add(new AddressBO("Rue 1", null, null, "33300", "Bordeaux", false));
		customer = new CustomerBO("335AURES", addressList, "0558567272", "john doe", false);
		carrier = carrierService.create(carrier);
		customer = customerService.create(customer);
		deliveryBO = new DeliveryBO(carrier, customer, 5,12.25,customer.getCustomerListDeliveryAddress().get(0));
		deliveryBO = deliveryService.create(deliveryBO);
	}

	@AfterEach
	void afterEach() {
		deliveryRepository.delete(DeliveryMapper.INSTANCE.deliveryBOToDelivery(deliveryBO));
	}

	@Test
	void createDelivery_CreateNewDeliveryInDatabase() throws Exception {		
		Optional<Delivery> result = deliveryRepository.findById(deliveryBO.getIdDelivery());
		assertTrue(result.isPresent());
		assertEquals(deliveryBO.getCustomer().getCustomerNumber(), result.get().getCustomer().getCustomerNumber());
		assertEquals(deliveryBO.getCarrier().getIdCarrier(), result.get().getCarrier().getIdCarrier());
	}

	@Test
	void updateDelivery_UpdateDeliveryInDatabase() throws Exception {		
		deliveryBO.setNumberOfPackage(15);
		DeliveryBO result = deliveryService.createOrUpdate(deliveryBO);
		assertEquals(deliveryBO.getIdDelivery(), result.getIdDelivery());
		assertEquals(deliveryBO.getNumberOfPackage(), result.getNumberOfPackage());
		assertEquals(15, result.getNumberOfPackage());
	}
	@Test
	void countDelivery_countDeliveryInDatabase() throws Exception {		
		assertEquals(deliveryRepository.findAll().size(), deliveryService.count());
	}
	
	@Test
	void findAllByCarrierAndCreateDateDelivery() throws Exception {
		
		DeliveryBO deliveryBOO = new DeliveryBO(carrier, customer, 8,2.5,customer.getCustomerListDeliveryAddress().get(0));
		//deliveryBOO.setCreateDateDelivery(deliveryBOO.getCreateDateDelivery().minusDays(1));
		deliveryBOO = deliveryService.create(deliveryBOO);
		
		LocalDate dateTest = LocalDate.now();
		logger.info("date delivery = " +deliveryBO.getCreateDateDelivery() + "   Date :"+dateTest.toString() );
		
		List<DeliveryBO> actualNumberOfDelivery= deliveryService.findAllByCarrierAndCreateDateDelivery(carrier.getIdCarrier(), dateTest);
		//List<Delivery> actualNumberOfDelivery=deliveryRepository.findAllByCarrierAndCreateDateDelivery(carrier, dateTest);
		
		assertEquals(deliveryBO.getIdDelivery(), actualNumberOfDelivery.get(0).getIdDelivery());
		assertEquals(2, actualNumberOfDelivery.size());
		
		
	}
	
}
