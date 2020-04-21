package com.sandbox.delivery.services.bo;

import java.util.Date;

public class DeliveryBO {
	private long idDelivery;

	private Date createDateDelivery;

	private CarrierBO carrier;

	private CustomerBO customer;

	private int numberOfPackage = 0;

	public DeliveryBO(CarrierBO carrier, CustomerBO customer, int numberOfPackage) {
		this.createDateDelivery = new Date();
		this.carrier = carrier;
		this.customer = customer;
		this.numberOfPackage = numberOfPackage;
	}

	public DeliveryBO() {
	}

	public long getIdDelivery() {
		return idDelivery;
	}

	public void setIdDelivery(long id) {
		this.idDelivery = id;
	}

	public Date getCreateDateDelivery() {
		return createDateDelivery;
	}

	public void setCreateDateDelivery(Date createDateDelivery) {
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

}
