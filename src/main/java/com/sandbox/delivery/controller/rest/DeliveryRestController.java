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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sandbox.delivery.services.DeliveryService;
import com.sandbox.delivery.services.bo.CustomerBO;
import com.sandbox.delivery.services.bo.DeliveryBO;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DeliveryRestController {

	@Autowired
	private DeliveryService deliveryService;

	@GetMapping(path = "/deliveries")
	public List<DeliveryBO> getAllDeliveries() {
		return deliveryService.getAll();
	}

	@PostMapping(path = "/delivery/")
	@ResponseBody
	public ResponseEntity<DeliveryBO> createDelivery(@RequestBody DeliveryBO delivery,
			UriComponentsBuilder uriBuilder) {
		DeliveryBO deliveryCreate = this.deliveryService.create(delivery);
		URI uri = uriBuilder.path("/delivery/{idDelivery}").buildAndExpand(deliveryCreate.getIdDelivery()).toUri();
		return ResponseEntity.created(uri).body(deliveryCreate);
	}
	@PutMapping(path = "/delivery/{idDelivery}")
	public ResponseEntity<DeliveryBO>updateDelivery(@RequestBody DeliveryBO delivery,
			UriComponentsBuilder uriBuilder){
		DeliveryBO deliveryUpdate = this.deliveryService.createOrUpdate(delivery);
		URI uri = uriBuilder.path("/delivery/{idDelivery}").buildAndExpand(deliveryUpdate.getIdDelivery()).toUri();
		return ResponseEntity.created(uri).body(deliveryUpdate);
	}
	
	
	@DeleteMapping(path = "/delivery/{idDelivery}")
	public void deleteDeliveryById(@PathVariable long idDelivery) {
		this.deliveryService.deleteDeliveryById(idDelivery);
	}
}