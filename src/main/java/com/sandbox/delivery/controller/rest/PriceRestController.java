package com.sandbox.delivery.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandbox.delivery.services.PriceService;
import com.sandbox.delivery.services.bo.PriceBO;

@CrossOrigin(origins = "http://5and8ox.com:4200")
@RestController
public class PriceRestController {

	@Autowired
	private PriceService priceService;
	
	@GetMapping(value = "/price")
	public List<PriceBO> getAllPrice() {
		return priceService.getAllPrice();
	}
}
