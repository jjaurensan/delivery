package com.sandbox.delivery.utilities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sandbox.delivery.services.PricingService;
import com.sandbox.delivery.services.bo.DeliveryBO;
import com.sandbox.delivery.services.bo.PriceBO;
import com.sandbox.delivery.services.bo.PricingBO;

@Component
public class PriceDelivery {

	@Autowired
	private PricingService pricingService;

	public double getDeliveryPrice(DeliveryBO delivery) {
		double price = 0.0;
		PricingBO p = pricingService.getPricingByCarrier(delivery.getCarrier());
		if (delivery.getCustomer().isArragement()) {
			price += p.getArragement();
		}
		List<PriceBO> listPriceCarrier = p.getListPrice();
		for (PriceBO priceBO : listPriceCarrier) {
			if(delivery.getWeight()>priceBO.getMinWeightValue()&& delivery.getWeight()<= priceBO.getMaxWeightValue()) {
				price += priceBO.getAmount();
			}
		}
		
			return price;
	}
}
