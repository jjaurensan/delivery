package com.sandbox.delivery.services;

import java.util.List;

import com.sandbox.delivery.services.bo.DeliveryBO;

public interface DeliveryService {

	DeliveryBO create(DeliveryBO deliveryBO);

	List<DeliveryBO> getAll();

	DeliveryBO createOrUpdate(DeliveryBO deliveryBO);

	void deleteDeliveryById(long idDelivery);

}
