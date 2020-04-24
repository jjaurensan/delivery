package com.sandbox.delivery.services.bo;

public class PriceBO {

	private long idPrice;
	
	private double amount;

	private double minWeightValue;

	private double maxWeightValue;

	public PriceBO(double amount, double minWeightValue, double maxWeightValue) {
		this.amount = amount;
		this.minWeightValue = minWeightValue;
		this.maxWeightValue = maxWeightValue;
	}
	public PriceBO() {
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getMinWeightValue() {
		return minWeightValue;
	}
	public void setMinWeightValue(double minWeightValue) {
		this.minWeightValue = minWeightValue;
	}
	public double getMaxWeightValue() {
		return maxWeightValue;
	}
	public void setMaxWeightValue(double maxWeightValue) {
		this.maxWeightValue = maxWeightValue;
	}
	public long getIdPrice() {
		return idPrice;
	}
	public void setIdPrice(long idPrice) {
		this.idPrice = idPrice;
	}
	
}
