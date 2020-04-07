package com.sandbox.delivery.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandbox.delivery.entities.Delivery;
import com.sandbox.delivery.repositories.DeliveryRepository;

@Service
public class DeliveryService {
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	@Transactional
	public Delivery create(Delivery delivery) {
		return deliveryRepository.save(delivery);
	}

	public List<Delivery> getAll() {
		return (List<Delivery>) deliveryRepository.findAll();
	}
}
