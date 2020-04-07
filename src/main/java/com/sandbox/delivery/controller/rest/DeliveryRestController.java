package com.sandbox.delivery.controller.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sandbox.delivery.entities.Delivery;
import com.sandbox.delivery.services.DeliveryService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class DeliveryRestController {

	@Autowired
	private DeliveryService deliveryService;
	
	@GetMapping(path = "/deliveries")
	private List<Delivery> getAllDeliveries(){
		return deliveryService.getAll();
	}
	@PostMapping(path = "/delivery/")
	private ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery, UriComponentsBuilder uriBuilder){
		Delivery deliveryCreate = this.deliveryService.create(delivery);
		URI uri = uriBuilder.path("/delivery/{idDelivery}").buildAndExpand(deliveryCreate.getIdDelivery()).toUri();
		return ResponseEntity.created(uri).body(deliveryCreate);
	}
}
