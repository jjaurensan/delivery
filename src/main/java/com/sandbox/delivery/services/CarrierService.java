package com.sandbox.delivery.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandbox.delivery.entities.Carrier;
import com.sandbox.delivery.repositories.CarrierRepository;

@Service
public class CarrierService {

	@Autowired
	private CarrierRepository carrierRepository;

	public Carrier create(Carrier carrier) {
		return carrierRepository.save(carrier);
	}

	public Carrier find(Carrier carrier) {
		Optional<Carrier> resultCarrier=carrierRepository.findById(carrier.getIdCarrier()) ;
		if(resultCarrier.isPresent()) {
			return resultCarrier.get();
		}
		return null;
	}

	public Carrier update(Carrier carrier) {
		return carrierRepository.save(carrier);
	}

	public List<Carrier> getAll() {
		return (List<Carrier>) carrierRepository.findAll();
	}

	public Carrier getById(long idCarrier) {
		Optional<Carrier> resultCarrier=carrierRepository.findById(idCarrier) ;
		if(resultCarrier.isPresent()) {
			return resultCarrier.get();
		}
		return null;
	}

	public void deleteById(long idCarrier) {
		carrierRepository.deleteById(idCarrier);
	}
	
	
	
}
