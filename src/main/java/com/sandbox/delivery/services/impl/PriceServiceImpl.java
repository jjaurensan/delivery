package com.sandbox.delivery.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandbox.delivery.mapper.PriceMapper;
import com.sandbox.delivery.persistent.entities.Price;
import com.sandbox.delivery.persistent.repositories.PriceRepository;
import com.sandbox.delivery.services.PriceService;
import com.sandbox.delivery.services.bo.PriceBO;

@Service("PriceService")
public class PriceServiceImpl implements PriceService {

	@Autowired
	private PriceRepository priceRepository;

	@Transactional
	public PriceBO create(PriceBO priceBO) {
		Price p = priceRepository.save(PriceMapper.PRICE_MAPPER.priceBOToPrice(priceBO));
		return PriceMapper.PRICE_MAPPER.priceToPriceBO(p);
	}

	@Override
	public PriceBO update(PriceBO priceBO) {
		Price p = priceRepository.save(PriceMapper.PRICE_MAPPER.priceBOToPrice(priceBO));
		return PriceMapper.PRICE_MAPPER.priceToPriceBO(p);
	}

	@Override
	public List<PriceBO> createListPrice(List<PriceBO> listPriceBO) {
		List<Price> p = PriceMapper.PRICE_MAPPER.listPriceBOToListPrice(listPriceBO);
		return PriceMapper.PRICE_MAPPER.listPriceToListPriceBO((List<Price>) priceRepository.saveAll(p));
	}

	@Override
	public List<PriceBO> getAllPrice() {
		return PriceMapper.PRICE_MAPPER.listPriceToListPriceBO((List<Price>) priceRepository.findAll());
	}

}
