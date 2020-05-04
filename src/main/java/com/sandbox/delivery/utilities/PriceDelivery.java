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

		double priceForDelivery = 0.0;
		PricingBO pricingGrid = pricingService.getPricingByCarrier(delivery.getCarrier());
		priceForDelivery += getPriceForWeight(delivery, pricingGrid.getListPrice());

		if (delivery.getCustomer().isArragement()) {
			priceForDelivery += pricingGrid.getArragement();
		}
		if (delivery.getAddress().isFloor()) {
			priceForDelivery += pricingGrid.getFloor();
		}
		return priceForDelivery;
	}

	private double getPriceForWeight(DeliveryBO delivery, List<PriceBO> listPriceCarrier) {
		double weightDelivery = delivery.getWeight();
		double priceForWeight = 0.0;
		for (PriceBO categoryPriceWeight : listPriceCarrier) {
			double minWeight = categoryPriceWeight.getMinWeightValue();
			double maxWeight = categoryPriceWeight.getMaxWeightValue();

			if ((weightDelivery > minWeight && weightDelivery <= maxWeight) 
					|| (weightDelivery <= minWeight && priceForWeight == 0)
					|| (weightDelivery > maxWeight)) {
				priceForWeight = categoryPriceWeight.getAmount();
			}
		}
		return priceForWeight;
	}
}
