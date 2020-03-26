package com.sandbox.delivery.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sandbox.delivery.entities.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

}
