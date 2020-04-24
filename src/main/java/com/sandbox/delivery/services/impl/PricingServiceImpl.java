package com.sandbox.delivery.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandbox.delivery.mapper.PricingMapper;
import com.sandbox.delivery.persistent.entities.Pricing;
import com.sandbox.delivery.persistent.repositories.PricingRepository;
import com.sandbox.delivery.services.PricingService;
import com.sandbox.delivery.services.bo.CarrierBO;
import com.sandbox.delivery.services.bo.PricingBO;
@Service("PricingService")
public class PricingServiceImpl implements PricingService {
	
	@Autowired
	private PricingRepository pricingRepository;

	@Transactional
	public PricingBO create(PricingBO pricingBO) {
		Pricing p = pricingRepository.save(PricingMapper.PRICING_MAPPER.pricingBoToPricing(pricingBO));
		return PricingMapper.PRICING_MAPPER.pricingToPricingBO(p);
	}

	@Override
	public PricingBO update(PricingBO pricingBO) {
		Pricing p = pricingRepository.save(PricingMapper.PRICING_MAPPER.pricingBoToPricing(pricingBO));
		return PricingMapper.PRICING_MAPPER.pricingToPricingBO(p);
	}

	@Override
	public PricingBO getPricingById(long idPrincingBO) {
		Pricing p = pricingRepository.findById(idPrincingBO).get();
		return PricingMapper.PRICING_MAPPER.pricingToPricingBO(p);
	}

	@Override
	public PricingBO getPricingByCarrier(CarrierBO carrierBO) {
		// TODO Auto-generated method stub
		return null;
	}

}
