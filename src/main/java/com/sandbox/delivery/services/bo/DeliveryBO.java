package com.sandbox.delivery.services.bo;

import java.time.LocalDate;

public class DeliveryBO {
	private long idDelivery;

	private LocalDate createDateDelivery;

	private CarrierBO carrier;

	private CustomerBO customer;
	
	private AddressBO address;

	private int numberOfPackage = 0;

	private double weight = 0;

	private double price = 0;

	public DeliveryBO(CarrierBO carrier, CustomerBO customer, int numberOfPackage, double weight,AddressBO address) {
		this.createDateDelivery =LocalDate.now();
		this.carrier = carrier;
		this.customer = customer;
		this.numberOfPackage = numberOfPackage;
		this.weight = weight;
		this.setAddress(address);
	}

	public DeliveryBO() {
	}

	public long getIdDelivery() {
		return idDelivery;
	}

	public void setIdDelivery(long id) {
		this.idDelivery = id;
	}

	public LocalDate getCreateDateDelivery() {
		return createDateDelivery;
	}

	public void setCreateDateDelivery(LocalDate createDateDelivery) {
		this.createDateDelivery = createDateDelivery;
	}

	public CarrierBO getCarrier() {
		return carrier;
	}

	public void setCarrier(CarrierBO carrier) {
		this.carrier = carrier;
	}

	public CustomerBO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBO customer) {
		this.customer = customer;
	}

	public int getNumberOfPackage() {
		return numberOfPackage;
	}

	public void setNumberOfPackage(int numberOfPackage) {
		this.numberOfPackage = numberOfPackage;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public AddressBO getAddress() {
		return address;
	}

	public void setAddress(AddressBO address) {
		this.address = address;
	}

}
