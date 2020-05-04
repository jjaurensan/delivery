package com.sandbox.delivery.utilities;

import java.util.Collections;
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

		if (delivery.getAddress().isFloor()) {
			price += p.getFloor();
		}

		List<PriceBO> listPriceCarrier = p.getListPrice();
		Collections.sort(listPriceCarrier);
		double weight = delivery.getWeight();
		double priceTemp = 0.0;
		for (PriceBO priceBO : listPriceCarrier) {
			double minWeight = priceBO.getMinWeightValue();
			double maxWeight = priceBO.getMaxWeightValue();

			if ((weight > minWeight && weight <= maxWeight) 
					|| (weight <= minWeight && priceTemp == 0)
					|| (weight > maxWeight)) {
				priceTemp = priceBO.getAmount();
			}

		}
		price += priceTemp;
		return price;
	}
}
