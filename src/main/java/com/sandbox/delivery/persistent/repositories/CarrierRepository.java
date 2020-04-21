package com.sandbox.delivery.persistent.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sandbox.delivery.persistent.entities.Carrier;
@Repository
public interface CarrierRepository extends CrudRepository<Carrier, Long> {

}
