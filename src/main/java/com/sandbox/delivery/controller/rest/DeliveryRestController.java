package com.sandbox.delivery.controller.rest;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.sandbox.delivery.services.bo.DeliveryBO;
import com.sandbox.delivery.utilities.PriceDelivery;

@CrossOrigin(origins = "http://5and8ox.com:4200")
@RestController
public class DeliveryRestController {

	@Autowired
	private DeliveryService deliveryService;
	
	@Autowired
	private PriceDelivery priceDelivery;

	@GetMapping(path = "/deliveries")
	public List<DeliveryBO> getAllDeliveries() {
		return deliveryService.getAll();
	}
	
	final Logger logger = LoggerFactory.getLogger(DeliveryRestController.class);

	@PostMapping(path = "/delivery/")
	@ResponseBody
	public ResponseEntity<DeliveryBO> createDelivery(@RequestBody DeliveryBO delivery,
			UriComponentsBuilder uriBuilder) {
		logger.warn("Valeur de date " + delivery.getCreateDateDelivery());
		delivery.setCreateDateDelivery(LocalDate.parse(delivery.getCreateDateDelivery().toString()));
		double price = priceDelivery.getDeliveryPrice(delivery);
		delivery.setPrice(price);
		DeliveryBO deliveryCreate = this.deliveryService.create(delivery);
		logger.warn("Valeur de date " + deliveryCreate.getCreateDateDelivery());
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
