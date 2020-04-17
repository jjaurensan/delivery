package com.sandbox.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandbox.delivery.entities.Delivery;

@Repository
public interface DeliveryRepository  extends JpaRepository<Delivery, Long>{

	

}
