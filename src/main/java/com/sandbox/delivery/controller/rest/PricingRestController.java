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

import com.sandbox.delivery.services.bo.PricingBO;
import com.sandbox.delivery.services.impl.PricingServiceImpl;

@CrossOrigin(origins = "http://5and8ox.com:4200")
@RestController
public class PricingRestController {

	@Autowired
	private PricingServiceImpl priceService;

	@GetMapping(value = "/pricing")
	public List<PricingBO> getAllPricing() {
		return priceService.getAll();
	}

	@PostMapping(value = "/pricing/")
	@ResponseBody
	public ResponseEntity<PricingBO> createPricing(@RequestBody PricingBO pricing, UriComponentsBuilder uriBuilder) {
		PricingBO pricingBOCreate = this.priceService.create(pricing);
		URI uri = uriBuilder.path("/pricing/{idPricing}").buildAndExpand(pricingBOCreate.getIdPricing()).toUri();
		return ResponseEntity.created(uri).body(pricingBOCreate);
	}

	@PutMapping(value = "/pricing/{idPricing}")
	@ResponseBody
	public ResponseEntity<PricingBO> updatePricing(@RequestBody PricingBO pricing, UriComponentsBuilder uriBuilder) {
		PricingBO pricingBOCreate = this.priceService.update(pricing);
		URI uri = uriBuilder.path("/pricing/{idPricing}").buildAndExpand(pricingBOCreate.getIdPricing()).toUri();
		return ResponseEntity.created(uri).body(pricingBOCreate);
	}
	
	
	@DeleteMapping(value = "/pricing/{idPricing}")
	public String deletePricing(@PathVariable long id) {
		this.priceService.deletePricingById(id);		
		return "Success";
	}

}
