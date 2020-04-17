package com.sandbox.delivery.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idDelivery;

	@Temporal(value = TemporalType.DATE)
	private Date createDateDelivery;

//	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Carrier.class)
//	@JoinTable(name = "carrier_delivery", joinColumns = @JoinColumn(name = "id_delivery"), inverseJoinColumns = @JoinColumn(name = "id_carrier"))
//	@Cascade({ CascadeType.ALL,CascadeType.SAVE_UPDATE })
//	private Carrier carrier;
//	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
//	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Customer.class)
//	@JoinTable(name = "customer_delivery", joinColumns = @JoinColumn(name = "id_delivery"), inverseJoinColumns = @JoinColumn(name = "id_customer"))
//	@Cascade({ CascadeType.ALL,CascadeType.SAVE_UPDATE })
//	private Customer customer;

	//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToOne(cascade = {CascadeType.ALL,CascadeType.PERSIST,CascadeType.MERGE})	
	private Carrier carrier;
	
	//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@OneToOne(cascade = {CascadeType.ALL,CascadeType.PERSIST,CascadeType.MERGE})	
	private Customer customer;
	
	private int numberOfPackage = 0;

	public Delivery(Carrier carrier, Customer customer, int numberOfPackage) {
		this.createDateDelivery = new Date();
		this.carrier = carrier;
		this.customer = customer;
		this.numberOfPackage = numberOfPackage;
	}

	public Delivery() {
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

}
