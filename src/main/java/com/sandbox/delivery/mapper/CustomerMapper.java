package com.sandbox.delivery.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.sandbox.delivery.persistent.entities.Customer;
import com.sandbox.delivery.services.bo.CustomerBO;

@Mapper
public interface CustomerMapper {
	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
	
	//@Mapping(source="customerListDeliveryAddress",target = "customerListDeliveryAddress")
	CustomerBO customerToCustomerBO(Customer customer);
	
	//@Mapping(source="customerListDeliveryAddress",target = "customerListDeliveryAddress")
	Customer customerBOToCustomer(CustomerBO customerBO);
	
	List <CustomerBO> listCustomerToListCustomerBO(List<Customer> listCustomer);
	List <Customer> listCustomerBOToListCustomer(List<CustomerBO> listCustomerBO);
}
