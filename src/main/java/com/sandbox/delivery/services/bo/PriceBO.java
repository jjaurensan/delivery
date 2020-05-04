package com.sandbox.delivery.services.bo;

import java.util.Comparator;

public class PriceBO implements Comparable<PriceBO> {

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
	@Override
	public String toString() {
		return "PriceBO [amount=" + amount + ", minWeightValue=" + minWeightValue + ", maxWeightValue=" + maxWeightValue
				+ "]";
	}
	@Override
	public int compareTo(PriceBO o) {
		return (int) (this.minWeightValue - o.minWeightValue);
	}
	
}
