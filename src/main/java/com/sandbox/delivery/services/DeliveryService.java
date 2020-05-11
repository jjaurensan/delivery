package com.sandbox.delivery.services;

import java.util.Date;
import java.util.List;

import com.sandbox.delivery.services.bo.DeliveryBO;

public interface DeliveryService {

	DeliveryBO create(DeliveryBO deliveryBO);

	List<DeliveryBO> getAll();

	List<DeliveryBO> getAllByIdCarrier(long idCarrier);

	List<DeliveryBO> findAllByCreateDateDelivery(Date createDateDelivery);

	long count();

	DeliveryBO createOrUpdate(DeliveryBO deliveryBO);

	void deleteDeliveryById(long idDelivery);

}
