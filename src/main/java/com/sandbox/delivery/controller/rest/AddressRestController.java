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

import com.sandbox.delivery.services.AddressService;
import com.sandbox.delivery.services.bo.AddressBO;

@CrossOrigin(origins = {"http://172.20.128.22:4200","http://172.20.128.22:80","http://localhost:80"})
@RestController
public class AddressRestController {

	@Autowired
	private AddressService addressService;

	@GetMapping(path = "/address")
	public List<AddressBO> getListOfAddress() {
		return addressService.getAllAddress();
	}

	@GetMapping(path = "/address/{idAddress}")
	public AddressBO getAddressById(@PathVariable long idAddress) {
		return addressService.getAddressById(idAddress);
	}

	@PostMapping(path = "/address/")
	public ResponseEntity<AddressBO> createAddress(@RequestBody AddressBO address, UriComponentsBuilder uriBuilder) {
		AddressBO addressCreate = this.addressService.create(address);
		URI uri = uriBuilder.path("/address/{idAddress}").buildAndExpand(addressCreate.getIdAddress()).toUri();
		return ResponseEntity.created(uri).body(addressCreate);
	}

	@PutMapping(path = "/address/{idAddress}")
	public ResponseEntity<AddressBO> updateAddress(@RequestBody AddressBO address, UriComponentsBuilder uriBuilder) {
		AddressBO addressUpdate = this.addressService.update(address);
		URI uri = uriBuilder.path("/address/{idAddress}").buildAndExpand(addressUpdate.getIdAddress()).toUri();
		return ResponseEntity.created(uri).body(addressUpdate);
	}
}
