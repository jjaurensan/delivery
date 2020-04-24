package com.sandbox.delivery.services;

import com.sandbox.delivery.services.bo.CarrierBO;
import com.sandbox.delivery.services.bo.PricingBO;

public interface PricingService {
	
	PricingBO create(PricingBO pricingBO);
	
	PricingBO update(PricingBO pricingBO);
	
	PricingBO getPricingById(long idPrincingBO);
	
	PricingBO getPricingByCarrier(CarrierBO carrierBO);

}
