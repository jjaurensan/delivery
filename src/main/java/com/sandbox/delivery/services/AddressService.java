package com.sandbox.delivery.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandbox.delivery.entities.Address;
import com.sandbox.delivery.repositories.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	public Address create(Address address) {
		return addressRepository.save(address);
	}

	public List<Address> createListAddress(List<Address> listAddress) {
		return (List<Address>) addressRepository.saveAll(listAddress);

	}

	public void update(Address address) {
		addressRepository.save(address);

	}
}
