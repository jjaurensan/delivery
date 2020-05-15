package com.sandbox.delivery.services;

import java.util.List;

import com.sandbox.delivery.services.bo.CarrierBO;

public interface CarrierService {

	CarrierBO create(CarrierBO carrierBO);

	CarrierBO find(CarrierBO carrierBO);

	CarrierBO update(CarrierBO carrierBO);

	List<CarrierBO> getAll();

	CarrierBO getById(long idCarrierBO);

	void deleteById(long idCarrierBO);

	void deleteAll();
}
