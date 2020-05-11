package com.sandbox.delivery.services.bo;

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
	public int compareTo(PriceBO o) {
		return (int) (this.minWeightValue - o.minWeightValue);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(maxWeightValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(minWeightValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PriceBO other = (PriceBO) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if ((Double.doubleToLongBits(maxWeightValue) != Double.doubleToLongBits(other.maxWeightValue)))
			return false;
		if (Double.doubleToLongBits(minWeightValue) != Double.doubleToLongBits(other.minWeightValue))
			return false;
		return true;
	}
	
}
