package com.sandbox.delivery.persistent.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pricing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long idPricing;

	private double arragement;

	private double floor;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "price_carrier", 
	joinColumns = @JoinColumn(name = "id_carrier"), 
	inverseJoinColumns = @JoinColumn(name = "id_price"))
	private List<Price> listPrice = new ArrayList<>();

	@OneToOne
	private Carrier carrier;

	public Pricing() {
	}

	public Pricing(long idPricing, double arragement, double floor, List<Price> listPrice, Carrier carrier) {
		this.idPricing = idPricing;
		this.arragement = arragement;
		this.floor = floor;
		this.listPrice = listPrice;
		this.carrier = carrier;
	}

	public long getIdPricing() {
		return idPricing;
	}

	public void setIdPricing(long idPricing) {
		this.idPricing = idPricing;
	}

	public double getArragement() {
		return arragement;
	}

	public void setArragement(double arragement) {
		this.arragement = arragement;
	}

	public double getFloor() {
		return floor;
	}

	public void setFloor(double floor) {
		this.floor = floor;
	}

	public List<Price> getListPrice() {
		return listPrice;
	}

	public void setListPrice(List<Price> listPrice) {
		this.listPrice = listPrice;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

}
