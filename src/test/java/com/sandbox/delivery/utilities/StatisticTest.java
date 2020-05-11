package com.sandbox.delivery.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sandbox.delivery.services.CarrierService;
import com.sandbox.delivery.services.CustomerService;
import com.sandbox.delivery.services.DeliveryService;
import com.sandbox.delivery.services.PricingService;
import com.sandbox.delivery.services.bo.AddressBO;
import com.sandbox.delivery.services.bo.CarrierBO;
import com.sandbox.delivery.services.bo.CustomerBO;
import com.sandbox.delivery.services.bo.DeliveryBO;
import com.sandbox.delivery.services.bo.PriceBO;
import com.sandbox.delivery.services.bo.PricingBO;

@SpringBootTest
class StatisticTest {

	final Logger logger = LoggerFactory.getLogger(StatisticTest.class);

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CarrierService carrierService;
	@Autowired
	private PricingService pricingService;
	@Autowired
	private DeliveryService deliveryService;

	@Autowired
	private Statistic statistic;

	private CarrierBO carrier;
	private CustomerBO customer;
	private List<AddressBO> addressList = new ArrayList<>();
	private DeliveryBO deliveryBO;
	List<PriceBO> listPriceBO = new ArrayList<PriceBO>();
	private PricingBO pricingBO;

	@Autowired
	private PriceDelivery priceDelivery;

	@BeforeEach
	void beforeEach() {
//		carrier = new CarrierBO("Cmainan", "rue 1", "Rue 2", "33320", "Pessac", "0556587272");
//		carrier = carrierService.create(carrier);
//
//		addressList.add(new AddressBO("Rue 1", null, null, "33300", "Bordeaux", false));
//		addressList.add(new AddressBO("Rue 2", null, null, "33300", "Bordeaux", true));
//		customer = new CustomerBO("335AURES", addressList, "0558567272", "john doe", false);
//		customer = customerService.create(customer);
//
//		listPriceBO.add(PriceMapper.PRICE_MAPPER.priceToPriceBO(new Price(5.0, 10, 15)));
//		listPriceBO.add(PriceMapper.PRICE_MAPPER.priceToPriceBO(new Price(4.0, 1, 10)));
//		listPriceBO.add(PriceMapper.PRICE_MAPPER.priceToPriceBO(new Price(6.0, 15, 25)));
//
//		pricingBO = new PricingBO(6, 8, listPriceBO, carrier);
//		pricingBO = pricingService.create(pricingBO);
//
//		deliveryBO = new DeliveryBO(carrier, customer, 5, 12.25, customer.getCustomerListDeliveryAddress().get(0));
//		deliveryBO.setPrice(priceDelivery.getDeliveryPrice(deliveryBO));
//		deliveryBO = deliveryService.create(deliveryBO);
	}
	@AfterEach
	void afterEach() {
		
	}

	@Test
	void getTotalNumberOfDelivery() {
		long totalDelivery = statistic.getTotalNumberOfDelivery();
		assertEquals(deliveryService.getAll().size(), totalDelivery);
	}

	@Test
	void getTotalAmountOfDelivery() {
		double totalAmountDelivery = statistic.getTotalAmountOfDelivery();
		assertEquals(deliveryService.getAll().size() * 5, totalAmountDelivery);
	}

	@Test
	void getTotalAmountOfDeliveryForDate() {
		LocalDate testDate = LocalDate.of(2020, Month.MAY, 04);
		logger.info("Date de test : " + testDate.toString());
		double totalAmountDelivery = 0;
		totalAmountDelivery = statistic.getTotalAmountOfDeliveryForDate(testDate);
		assertEquals(5, totalAmountDelivery);
	}
	@Test
	void getTotalAmountOfDeliveryForMonth() {
		double totalAmountDelivery = 0;
		totalAmountDelivery = statistic.getTotalAmountOfDeliveryForMonth(Month.MAY);
		assertEquals(220, totalAmountDelivery);
	}

}
