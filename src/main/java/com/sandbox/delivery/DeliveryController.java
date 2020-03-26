package com.sandbox.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sandbox.delivery.services.CarrierService;
import com.sandbox.delivery.services.DeliveryService;

@Controller
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;
	
	@Autowired
	private CarrierService carrierservice;
	
	@GetMapping(path = "/delivery")
	public String getDelivery(Model model) {
		model.addAttribute("carriers",carrierservice.getAll());
		//List<Delivery> deliveries = deliveryService.getAll();
		//model.addAttribute("deliveries",deliveries);
		
		
		return"fragment/delivery";
	}
}
