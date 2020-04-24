package com.sandbox.delivery.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sandbox.delivery.mapper.PriceMapper;
import com.sandbox.delivery.persistent.entities.Price;
import com.sandbox.delivery.persistent.repositories.PriceRepository;
import com.sandbox.delivery.services.bo.PriceBO;
import com.sandbox.delivery.services.impl.PriceServiceImpl;

@SpringBootTest
class PriceServiceTest {

	@Autowired
	private PriceRepository priceRepository;

	@Autowired
	private PriceServiceImpl priceService;

	private PriceBO priceBO;
	private Price priceOne;
	private Price priceTwo;

	@BeforeEach
	private void BeforeEach() {
		priceOne = priceRepository.save(new Price(5.0, 10, 15));
		priceTwo = priceRepository.save(new Price(6.0, 15, 25));
	}

	@AfterEach
	private void afterEach() {
		priceRepository.deleteAll();
	}

	@Test
	void Create() {
		priceBO = new PriceBO(10, 30, 35);
		PriceBO result = priceService.create(priceBO);
		assertEquals(priceBO.getAmount(), result.getAmount());
	}

	@Test
	void Update() {
		priceBO = PriceMapper.PRICE_MAPPER.priceToPriceBO(priceOne);
		priceBO.setAmount(6.5);
		PriceBO result = priceService.update(priceBO);
		assertEquals(priceBO.getAmount(), result.getAmount());
	}

	@Test
	void CreateListPrice() {
		List<PriceBO> listPriceBO = new ArrayList<PriceBO>();
		listPriceBO.add(PriceMapper.PRICE_MAPPER.priceToPriceBO(priceOne));
		listPriceBO.add(PriceMapper.PRICE_MAPPER.priceToPriceBO(priceTwo));
		List<PriceBO> listResult = priceService.createListPrice(listPriceBO);
		assertFalse(listResult.isEmpty());
	}

}
