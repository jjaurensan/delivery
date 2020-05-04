package com.sandbox.delivery.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sandbox.delivery.mapper.PriceMapper;
import com.sandbox.delivery.persistent.entities.Price;
import com.sandbox.delivery.persistent.repositories.PricingRepository;
import com.sandbox.delivery.services.bo.CarrierBO;
import com.sandbox.delivery.services.bo.PriceBO;
import com.sandbox.delivery.services.bo.PricingBO;
@SpringBootTest
class PricingServiceTest {
	
	@Autowired
	private PricingRepository pricingRepository;
	
	@Autowired
	private PricingService pricingService;
	
	@Autowired
	private CarrierService carrierService;
	
	PricingBO pricingBO;
	List<PriceBO> listPriceBO = new ArrayList<PriceBO>();
	private CarrierBO carrier;
	
	@BeforeEach
	private void BeforeEach() {
		carrier = new CarrierBO("Cmainan", "rue 1", "Rue 2", "33320", "Pessac", "0556587272");
		listPriceBO.add(PriceMapper.PRICE_MAPPER.priceToPriceBO(new Price(5.0, 10, 15)));
		listPriceBO.add(PriceMapper.PRICE_MAPPER.priceToPriceBO(new Price(6.0, 15, 25)));
		//listPriceBO = priceService.createListPrice(listPriceBO);
		carrier = carrierService.create(carrier);
	}

	@AfterEach
	private void afterEach() {
		pricingRepository.deleteAll();
	}

	@Test
	void Create() {
		pricingBO = new PricingBO(5.0, 5.5, listPriceBO, carrier);
		PricingBO result = pricingService.create(pricingBO);
		assertEquals(pricingBO.getArragement(),result.getArragement());
	}

}
