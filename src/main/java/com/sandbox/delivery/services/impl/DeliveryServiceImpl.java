package com.sandbox.delivery.services.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandbox.delivery.mapper.DeliveryMapper;
import com.sandbox.delivery.persistent.entities.Delivery;
import com.sandbox.delivery.persistent.repositories.DeliveryRepository;
import com.sandbox.delivery.services.DeliveryService;
import com.sandbox.delivery.services.bo.DeliveryBO;

@Service("DeliveryService")
public class DeliveryServiceImpl implements DeliveryService {


	
	final DeliveryMapper mapper = DeliveryMapper.INSTANCE;

	@Autowired
	private EntityManager em;

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Transactional
	public DeliveryBO create(DeliveryBO deliveryBO) {
		Delivery d = mapper.deliveryBOToDelivery(deliveryBO);
		if (d.getIdDelivery() == 0) {			
			d=em.merge(d);
		} else {
			em.persist(d);
		}
		return mapper.deliveryToDeliveryBO(d);
	}

	public List<DeliveryBO> getAll() {
		return mapper.listDeliveryToListDeliveryBO(deliveryRepository.findAll());
	}

	@Transactional
	public DeliveryBO createOrUpdate(DeliveryBO deliveryBO) {
		Delivery d = mapper.deliveryBOToDelivery(deliveryBO);
		deliveryRepository.saveAndFlush(d);
		return mapper.deliveryToDeliveryBO(d);
	}

	@Override
	public void deleteDeliveryById(long idDelivery) {
		deliveryRepository.deleteById(idDelivery);		
	}
}
