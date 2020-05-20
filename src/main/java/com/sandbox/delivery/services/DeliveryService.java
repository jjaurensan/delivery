package com.sandbox.delivery.services;

import java.time.LocalDate;
import java.util.List;

import com.sandbox.delivery.services.bo.DeliveryBO;

public interface DeliveryService {

	DeliveryBO create(DeliveryBO deliveryBO);

	List<DeliveryBO> getAll();

	List<DeliveryBO> getAllByIdCarrier(long idCarrier);
	

	long count();

	DeliveryBO createOrUpdate(DeliveryBO deliveryBO);

	void deleteDeliveryById(long idDelivery);

	void deleteAll();

	List<DeliveryBO> findAllByCreateDateDelivery(LocalDate createDateDelivery);

	
	List<DeliveryBO> findAllByCarrierAndCreateDateDelivery(long idCarrier,LocalDate createDateDelivery);

}
