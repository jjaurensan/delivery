package com.sandbox.delivery.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandbox.delivery.entities.Address;
import com.sandbox.delivery.repositories.AddressRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Transactional
	public Address create(Address address) {
		return addressRepository.save(address);
	}

	public List<Address> createListAddress(List<Address> listAddress) {
		return (List<Address>) addressRepository.saveAll(listAddress);
	}

	public Address update(Address address) {
		return addressRepository.save(address);
	}

	public List<Address> getAllAddress() {
		return (List<Address>) addressRepository.findAll();
	}
	
	public Address getAddressById(long idAddress) {
		return addressRepository.findById(idAddress).get();
	}
	@Transactional
	public void deleteAdrress(Address address) {
		addressRepository.delete(address);
	}
}
