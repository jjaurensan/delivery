package com.sandbox.delivery.services;

import java.util.List;

import com.sandbox.delivery.services.bo.AddressBO;

public interface AddressService {

	AddressBO create(AddressBO addressBO);

	List<AddressBO> createListAddress(List<AddressBO> listAddressBO);

	AddressBO update(AddressBO addressBO);

	List<AddressBO> getAllAddress();

	AddressBO getAddressById(long idAddress);

	void deleteAdrress(AddressBO addressBO);

	void deleteAll();
}
