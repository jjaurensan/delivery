package com.sandbox.delivery.controller.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sandbox.delivery.exception.CarrierExistExeception;
import com.sandbox.delivery.exception.CarrierNoExistExeception;
import com.sandbox.delivery.services.CarrierService;
import com.sandbox.delivery.services.bo.CarrierBO;

@CrossOrigin(origins = {"*","http://localhost:4200","http://5and8ox.com:4200"})
@RestController
public class CarrierRestController {
	@Autowired
	private CarrierService carrierService;

	@GetMapping(path = "/carriers")
	public List<CarrierBO> getListOfCarrier() {
		return carrierService.getAll();
	}

	@GetMapping(path = "/carrier/{idCarrier}")
	public CarrierBO getCarrierById(@PathVariable long idCarrier) {
		return carrierService.getById(idCarrier);
	}

	@PostMapping(path = "/carrier/")
	public ResponseEntity<CarrierBO> createCarrier(@RequestBody CarrierBO carrier, UriComponentsBuilder uriBuilder)
			throws CarrierExistExeception, CarrierNoExistExeception {
		CarrierBO carrierCreate = this.carrierService.create(carrier);
		URI uri = uriBuilder.path("/carrier/{idCarrier}").buildAndExpand(carrierCreate.getIdCarrier()).toUri();
		return ResponseEntity.created(uri).body(carrierCreate);
	}

	@PutMapping(path = "/carrier/{idCarrier}")
	public ResponseEntity<CarrierBO> updateCarrier(@RequestBody CarrierBO carrier, UriComponentsBuilder uriBuilder) {
		CarrierBO carrierUpdate = this.carrierService.update(carrier);
		URI uri = uriBuilder.path("/carrier/{idCarrier}").buildAndExpand(carrierUpdate.getIdCarrier()).toUri();
		return ResponseEntity.created(uri).body(carrierUpdate);
	}

	@DeleteMapping(path = "/carrier/{idCarrier}")
	public void deleteByCode(@PathVariable long idCarrier) {
		this.carrierService.deleteById(idCarrier);
	}
}
