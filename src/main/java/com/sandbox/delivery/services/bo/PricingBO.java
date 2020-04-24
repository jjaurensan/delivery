package com.sandbox.delivery.services.bo;

import java.util.ArrayList;
import java.util.List;

public class PricingBO {

	private long idPricing;

	private double arragement;

	private double floor;
	private List<PriceBO> listPrice = new ArrayList<>();

	private CarrierBO carrier;

	public PricingBO() {
	}

	public PricingBO(double arragement, double floor, List<PriceBO> listPrice, CarrierBO carrier) {
		
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

	public List<PriceBO> getListPrice() {
		return listPrice;
	}

	public void setListPrice(List<PriceBO> listPrice) {
		this.listPrice = listPrice;
	}

	public CarrierBO getCarrier() {
		return carrier;
	}

	public void setCarrier(CarrierBO carrier) {
		this.carrier = carrier;
	}

}
