package com.sandbox.delivery.persistent.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandbox.delivery.persistent.entities.Carrier;
import com.sandbox.delivery.persistent.entities.Delivery;

@Repository
public interface DeliveryRepository  extends JpaRepository<Delivery, Long>{

	List<Delivery> findAllByCreateDateDelivery(Date createDateDelivery);
	
	List<Delivery> findAllByCreateDateDeliveryBetween(Date createDateDeliveryStart,Date createDateDeliveryEnd);
		
	List<Delivery> findAllByCarrier(Carrier carrier);
	
	long count();

}
