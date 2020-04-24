package com.sandbox.delivery.services;

import java.util.List;

import com.sandbox.delivery.services.bo.PriceBO;

public interface PriceService {
	
	PriceBO create(PriceBO priceBO);
	
	PriceBO update(PriceBO priceBO);
		
	List<PriceBO> createListPrice(List<PriceBO> listPriceBO);
	
	List<PriceBO> getAllPrice();
	
	
}
