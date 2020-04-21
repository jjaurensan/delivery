package com.sandbox.delivery.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.sandbox.delivery.persistent.entities.Delivery;
import com.sandbox.delivery.services.bo.DeliveryBO;

@Mapper
public interface DeliveryMapper {
	
	DeliveryMapper INSTANCE = Mappers.getMapper(DeliveryMapper.class);

	@Mapping(source = "carrier", target = "carrier")
	@Mapping(source = "customer", target = "customer")
	DeliveryBO deliveryToDeliveryBO(Delivery delivery);

	@Mapping(source = "carrier", target = "carrier")
	@Mapping(source = "customer", target = "customer")
	Delivery deliveryBOToDelivery(DeliveryBO deliveryBO);

	List<DeliveryBO> listDeliveryToListDeliveryBO(List<Delivery> delivery);

	List<Delivery> listDeliveryBOToListDelivery(List<DeliveryBO> deliveryBO);
}
