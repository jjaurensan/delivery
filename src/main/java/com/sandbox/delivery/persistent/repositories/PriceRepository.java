package com.sandbox.delivery.persistent.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandbox.delivery.persistent.entities.Price;
@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

}
