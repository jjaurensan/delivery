package com.sandbox.delivery.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sandbox.delivery.persistent.entities.Address;
import com.sandbox.delivery.services.bo.AddressBO;

@Mapper
public interface AddressMapper {

	AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

	AddressBO addressToAddressBO(Address address);

	Address addressBOToAddress(AddressBO addressBO);

	List<AddressBO> listAddressToListAddressBO(List<Address> listAddress);

	List<Address> listAddressBOToListAddress(List<AddressBO> listAddressBO);

}
