package com.sandbox.delivery.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sandbox.delivery.entities.Carrier;
@Repository
public interface CarrierRepository extends CrudRepository<Carrier, Long> {

}
