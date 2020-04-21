package com.sandbox.delivery.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandbox.delivery.mapper.AddressMapper;
import com.sandbox.delivery.persistent.entities.Address;
import com.sandbox.delivery.persistent.repositories.AddressRepository;
import com.sandbox.delivery.services.AddressService;
import com.sandbox.delivery.services.bo.AddressBO;

@Service("AddressService")
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Transactional
	public AddressBO create(AddressBO addressBO) {
		Address a = addressRepository.save(AddressMapper.INSTANCE.addressBOToAddress(addressBO));
		return AddressMapper.INSTANCE.addressToAddressBO(a);
	}

	public List<AddressBO> createListAddress(List<AddressBO> listAddressBO) {
		List<Address> a = AddressMapper.INSTANCE.listAddressBOToListAddress(listAddressBO);		
		return AddressMapper.INSTANCE.listAddressToListAddressBO((List<Address>) addressRepository.saveAll(a));
	}

	public AddressBO update(AddressBO addressBO) {
		Address a = addressRepository.save(AddressMapper.INSTANCE.addressBOToAddress(addressBO));
		return AddressMapper.INSTANCE.addressToAddressBO(a);
	}

	public List<AddressBO> getAllAddress() {		
		return AddressMapper.INSTANCE.listAddressToListAddressBO((List<Address>) addressRepository.findAll()) ;
	}
	
	public AddressBO getAddressById(long idAddress) {
		AddressBO result = AddressMapper.INSTANCE.addressToAddressBO(addressRepository.findById(idAddress).get());
		return result;
	}
	@Transactional
	public void deleteAdrress(AddressBO addressBO) {
		Address a = AddressMapper.INSTANCE.addressBOToAddress(addressBO);
		addressRepository.delete(a);
	}
}
