package com.sandbox.delivery.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sandbox.delivery.entities.Delivery;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery, Long> {

}
