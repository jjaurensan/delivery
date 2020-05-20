package com.sandbox.delivery.persistent.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandbox.delivery.persistent.entities.Carrier;
import com.sandbox.delivery.persistent.entities.Delivery;

@Repository
public interface DeliveryRepository  extends JpaRepository<Delivery, Long>{

	List<Delivery> findAllByCreateDateDelivery(LocalDate createDateDelivery);
	
	
	List<Delivery> findAllByCreateDateDeliveryBetween(LocalDate createDateDeliveryStart,LocalDate localDate);
		
	List<Delivery> findAllByCarrier(Carrier carrier);
	
		
	long count();

	List<Delivery> findAllByCarrierAndCreateDateDelivery(Carrier carrier,LocalDate createDateDelivery);
	
}
