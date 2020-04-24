package com.sandbox.delivery.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sandbox.delivery.persistent.entities.Price;
import com.sandbox.delivery.services.bo.PriceBO;

@Mapper
public interface PriceMapper {

	static final PriceMapper PRICE_MAPPER = Mappers.getMapper(PriceMapper.class);
	
	PriceBO priceToPriceBO(Price price);
	
	Price priceBOToPrice(PriceBO priceBo);
	
	List <PriceBO> listPriceToListPriceBO(List<Price> listPrice);
	List <Price> listPriceBOToListPrice(List<PriceBO> listPriceBO);
}
