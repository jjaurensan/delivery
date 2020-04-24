package com.sandbox.delivery.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sandbox.delivery.persistent.entities.Pricing;
import com.sandbox.delivery.services.bo.PricingBO;

@Mapper
public interface PricingMapper {
	static final PricingMapper PRICING_MAPPER = Mappers.getMapper(PricingMapper.class);

	PricingBO pricingToPricingBO(Pricing pricing);

	Pricing pricingBoToPricing(PricingBO pricingBO);
}
