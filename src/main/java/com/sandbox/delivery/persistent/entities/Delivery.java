package com.sandbox.delivery.persistent.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idDelivery;

	//@Temporal(value = TemporalType.DATE)
	private LocalDate createDateDelivery;

	// @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Carrier carrier;

	// @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Customer customer;

	// @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToOne(cascade = { CascadeType.DETACH})
	private Address address;

	private int numberOfPackage = 0;

	private double weight = 0;

	private double price = 0;

	public Delivery(Carrier carrier, Customer customer, int numberOfPackage, long weight,Address address) {
		this.createDateDelivery = LocalDate.now();
		this.carrier = carrier;
		this.customer = customer;
		this.numberOfPackage = numberOfPackage;
		this.weight = weight;
		this.address = address;
	}

	public Delivery() {
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

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
