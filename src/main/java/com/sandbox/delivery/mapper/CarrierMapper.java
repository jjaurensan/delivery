package com.sandbox.delivery.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sandbox.delivery.persistent.entities.Carrier;
import com.sandbox.delivery.services.bo.CarrierBO;

@Mapper
public interface CarrierMapper {
	CarrierMapper INSTANCE = Mappers.getMapper(CarrierMapper.class);
	
	CarrierBO carrierToCarrierBO(Carrier carrier);
	Carrier carrierBOToCarrier(CarrierBO carrierBO);
	
	List<CarrierBO> listCarrierToListCarrierBO(List<Carrier> listCarrier);
	List<Carrier> listCarrierBOToListCarrier(List<CarrierBO> listCarrierBO);
	
}
