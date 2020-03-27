package com.sandbox.delivery.controller.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sandbox.delivery.entities.Address;
import com.sandbox.delivery.services.AddressService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AddressRestController {

	@Autowired
	private AddressService addressService;

	@GetMapping(path = "/address")
	public List<Address> getListOfAddress() {
		return addressService.getAllAddress();
	}

	@GetMapping(path = "/address/{idAddress}")
	public Address getAddressById(@PathVariable long idAddress) {
		return addressService.getAddressById(idAddress);
	}

	@PostMapping(path = "/address/")
	public ResponseEntity<Address> createAddress(@RequestBody Address address, UriComponentsBuilder uriBuilder) {
		Address addressCreate = this.addressService.create(address);
		URI uri = uriBuilder.path("/address/{idAddress}").buildAndExpand(addressCreate.getIdAddress()).toUri();
		return ResponseEntity.created(uri).body(addressCreate);
	}

	@PutMapping(path = "/address/{idAddress}")
	public ResponseEntity<Address> updateAddress(@RequestBody Address address, UriComponentsBuilder uriBuilder) {
		Address addressUpdate = this.addressService.update(address);
		URI uri = uriBuilder.path("/address/{idAddress}").buildAndExpand(addressUpdate.getIdAddress()).toUri();
		return ResponseEntity.created(uri).body(addressUpdate);
	}
}
