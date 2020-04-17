package com.sandbox.delivery.services;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandbox.delivery.entities.Delivery;
import com.sandbox.delivery.repositories.DeliveryRepository;

@Service
public class DeliveryService {

	@Autowired
	private EntityManager em;

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Transactional
	public Delivery create(Delivery delivery) {
		if (delivery.getCarrier().getIdCarrier() > 0) {
			delivery = em.merge(delivery);
			
		} else {
			em.persist(delivery);
		}

		return delivery;
	}

	public List<Delivery> getAll() {
		return (List<Delivery>) deliveryRepository.findAll();
	}

	@Transactional
	public Delivery createOrUpdate(Delivery delivery) {
		return deliveryRepository.saveAndFlush(delivery);
	}
}
