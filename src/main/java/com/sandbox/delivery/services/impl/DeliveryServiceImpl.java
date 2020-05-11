package com.sandbox.delivery.services.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandbox.delivery.mapper.CarrierMapper;
import com.sandbox.delivery.mapper.DeliveryMapper;
import com.sandbox.delivery.persistent.entities.Carrier;
import com.sandbox.delivery.persistent.entities.Delivery;
import com.sandbox.delivery.persistent.repositories.DeliveryRepository;
import com.sandbox.delivery.services.CarrierService;
import com.sandbox.delivery.services.DeliveryService;
import com.sandbox.delivery.services.bo.DeliveryBO;

@Service("DeliveryService")
public class DeliveryServiceImpl implements DeliveryService {


	
	 static final DeliveryMapper MAPPER = DeliveryMapper.INSTANCE;

	@Autowired
	private EntityManager em;

	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	private CarrierService carrierService;

	@Transactional
	public DeliveryBO create(DeliveryBO deliveryBO) {
		Delivery d = MAPPER.deliveryBOToDelivery(deliveryBO);
		
		if (d.getIdDelivery() == 0) {			
			d=em.merge(d);
		} else {
			em.persist(d);
		}
		return MAPPER.deliveryToDeliveryBO(d);
	}

	public List<DeliveryBO> getAll() {
		return MAPPER.listDeliveryToListDeliveryBO(deliveryRepository.findAll());
	}
	
	public long count() {
		return deliveryRepository.count();		
	}

	@Transactional
	public DeliveryBO createOrUpdate(DeliveryBO deliveryBO) {
		Delivery d = MAPPER.deliveryBOToDelivery(deliveryBO);
		deliveryRepository.saveAndFlush(d);
		return MAPPER.deliveryToDeliveryBO(d);
	}

	@Override
	public void deleteDeliveryById(long idDelivery) {
		deliveryRepository.deleteById(idDelivery);		
	}

	@Override
	public List<DeliveryBO> findAllByCreateDateDelivery(Date createDateDelivery) {
		return MAPPER.listDeliveryToListDeliveryBO(deliveryRepository.findAllByCreateDateDelivery(createDateDelivery));
	}

	@Override
	public List<DeliveryBO> getAllByIdCarrier(long idCarrier) {
		 Carrier carrier = CarrierMapper.INSTANCE.carrierBOToCarrier(carrierService.getById(idCarrier));
		return MAPPER.listDeliveryToListDeliveryBO(deliveryRepository.findAllByCarrier(carrier));
	}
}
