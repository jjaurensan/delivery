package com.sandbox.delivery.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.sandbox.delivery.persistent.entities.Address;
import com.sandbox.delivery.services.bo.AddressBO;

class AddressMapperTest {

	@Test
	void shouldMapAddressToBO() {
		Address address = new Address("Rue 1", "Rue 2", "Rue3", "33300", "Bordeaux", false);

		AddressBO addressBO = AddressMapper.INSTANCE.addressToAddressBO(address);

		assertNotNull(addressBO);
		assertEquals("Rue 1", addressBO.getStreetOne());
		assertEquals("33300", addressBO.getZipCode());
		assertEquals("Bordeaux", addressBO.getCity());
	}

}
