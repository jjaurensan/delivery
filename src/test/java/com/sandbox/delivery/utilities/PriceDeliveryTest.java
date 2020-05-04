package com.sandbox.delivery.utilities;

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
import com.sandbox.delivery.services.CarrierService;
import com.sandbox.delivery.services.CustomerService;
import com.sandbox.delivery.services.PricingService;
import com.sandbox.delivery.services.bo.AddressBO;
import com.sandbox.delivery.services.bo.CarrierBO;
import com.sandbox.delivery.services.bo.CustomerBO;
import com.sandbox.delivery.services.bo.DeliveryBO;
import com.sandbox.delivery.services.bo.PriceBO;
import com.sandbox.delivery.services.bo.PricingBO;

@SpringBootTest
class PriceDeliveryTest {

	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CarrierService carrierService;
	@Autowired
	private PricingService pricingService;

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
		carrier = new CarrierBO("Cmainan", "rue 1", "Rue 2", "33320", "Pessac", "0556587272");
		carrier = carrierService.create(carrier);

		addressList.add(new AddressBO("Rue 1", null, null, "33300", "Bordeaux", false));
		addressList.add(new AddressBO("Rue 2", null, null, "33300", "Bordeaux", true));		
		customer = new CustomerBO("335AURES", addressList, "0558567272", "john doe", false);
		customer = customerService.create(customer);
		
		listPriceBO.add(PriceMapper.PRICE_MAPPER.priceToPriceBO(new Price(5.0, 10, 15)));
		listPriceBO.add(PriceMapper.PRICE_MAPPER.priceToPriceBO(new Price(4.0, 1, 10)));		
		listPriceBO.add(PriceMapper.PRICE_MAPPER.priceToPriceBO(new Price(6.0, 15, 25)));
		 
		
		pricingBO = new PricingBO(6, 8, listPriceBO, carrier);
		pricingBO = pricingService.create(pricingBO);

	}

	@AfterEach
	void afterEach() {
	}

	@Test
	void testGetDeliveryPriceWidthMiddleWeight() {
		deliveryBO = new DeliveryBO(carrier, customer, 5, 12.25, customer.getCustomerListDeliveryAddress().get(0));
		double priceResult = priceDelivery.getDeliveryPrice(deliveryBO);
		assertEquals(5, priceResult);
	}
	@Test
	void testGetDeliveryPriceWidthMiddleWeightAndFloor() {
		deliveryBO = new DeliveryBO(carrier, customer, 5, 12.25, customer.getCustomerListDeliveryAddress().get(1));
		double priceResult = priceDelivery.getDeliveryPrice(deliveryBO);
		assertEquals(5+pricingBO.getFloor(), priceResult);
	}
	@Test
	void testGetDeliveryPriceWidthMiddleWeightAndArrangement() {
		customer.setArragement(true);
		deliveryBO = new DeliveryBO(carrier, customer, 5, 12.25, customer.getCustomerListDeliveryAddress().get(0));
		double priceResult = priceDelivery.getDeliveryPrice(deliveryBO);
		assertEquals(5+pricingBO.getArragement(), priceResult);
	}
	@Test
	void testGetDeliveryPriceWidthMiddleWeightAndArrangementAndFloor() {
		customer.setArragement(true);
		deliveryBO = new DeliveryBO(carrier, customer, 5, 12.25, customer.getCustomerListDeliveryAddress().get(1));
		double priceResult = priceDelivery.getDeliveryPrice(deliveryBO);
		assertEquals(5+pricingBO.getArragement()+pricingBO.getFloor(), priceResult);
	}
	@Test
	void testGetDeliveryPriceWidthMaxWeight() {
		deliveryBO = new DeliveryBO(carrier, customer, 5,25, customer.getCustomerListDeliveryAddress().get(0));
		double priceResult = priceDelivery.getDeliveryPrice(deliveryBO);
		assertEquals(6, priceResult);
	}
	@Test
	void testGetDeliveryPriceWidthMinWeight() {
		deliveryBO = new DeliveryBO(carrier, customer, 5,1, customer.getCustomerListDeliveryAddress().get(0));
		double priceResult = priceDelivery.getDeliveryPrice(deliveryBO);
		assertEquals(4, priceResult);
	}
	@Test
	void testGetDeliveryPriceWidthOverMinWeight() {
		deliveryBO = new DeliveryBO(carrier, customer, 5,0.8, customer.getCustomerListDeliveryAddress().get(0));
		double priceResult = priceDelivery.getDeliveryPrice(deliveryBO);
		assertEquals(4, priceResult);
	}
	@Test
	void testGetDeliveryPriceWidthOverMaxWeight() {
		deliveryBO = new DeliveryBO(carrier, customer, 5,28, customer.getCustomerListDeliveryAddress().get(0));
		double priceResult = priceDelivery.getDeliveryPrice(deliveryBO);
		assertEquals(6, priceResult);
	}

}
