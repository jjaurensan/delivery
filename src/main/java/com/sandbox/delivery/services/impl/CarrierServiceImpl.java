package com.sandbox.delivery.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandbox.delivery.mapper.CarrierMapper;
import com.sandbox.delivery.persistent.entities.Carrier;
import com.sandbox.delivery.persistent.repositories.CarrierRepository;
import com.sandbox.delivery.services.CarrierService;
import com.sandbox.delivery.services.bo.CarrierBO;

@Service("CarrierService")
public class CarrierServiceImpl implements CarrierService {

	@Autowired
	private CarrierRepository carrierRepository;

	public CarrierBO create(CarrierBO carrierBO) {
		Carrier carrier = CarrierMapper.INSTANCE.carrierBOToCarrier(carrierBO);
		carrierRepository.save(carrier);
		return CarrierMapper.INSTANCE.carrierToCarrierBO(carrier);
	}

	public CarrierBO find(CarrierBO carrierBO) {
		Optional<Carrier> resultCarrier = carrierRepository.findById(carrierBO.getIdCarrier());
		if (resultCarrier.isPresent()) {
			return CarrierMapper.INSTANCE.carrierToCarrierBO(resultCarrier.get());
		}
		return null;
	}

	public CarrierBO update(CarrierBO carrierBO) {
		return create(carrierBO);
	}

	public List<CarrierBO> getAll() {
		return CarrierMapper.INSTANCE.listCarrierToListCarrierBO((List<Carrier>) carrierRepository.findAll());
	}

	public CarrierBO getById(long idCarrier) {
		Optional<Carrier> resultCarrier = carrierRepository.findById(idCarrier);
		if (resultCarrier.isPresent()) {
			return CarrierMapper.INSTANCE.carrierToCarrierBO(resultCarrier.get());
		}
		return null;
	}

	public void deleteById(long idCarrier) {
		carrierRepository.deleteById(idCarrier);
	}

	@Override
	public void deleteAll() {
		carrierRepository.deleteAll();
		
	}

}
