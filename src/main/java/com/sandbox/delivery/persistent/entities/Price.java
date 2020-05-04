package com.sandbox.delivery.persistent.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Price {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long idPrice;

	private double amount;

	private double minWeightValue;

	private double maxWeightValue;

	public Price() {
	}

	public Price(double amount, double minWeightValue, double maxWeightValue) {
		this.amount = amount;
		this.minWeightValue = minWeightValue;
		this.maxWeightValue = maxWeightValue;
	}

	public long getIdPrice() {
		return idPrice;
	}

	public void setIdPrice(long idPrice) {
		this.idPrice = idPrice;
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

	@Override
	public String toString() {
		return "Price [amount=" + amount + ", minWeightValue=" + minWeightValue + ", maxWeightValue=" + maxWeightValue
				+ "]";
	}

}
