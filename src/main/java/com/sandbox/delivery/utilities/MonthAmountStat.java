package com.sandbox.delivery.utilities;

public class MonthAmountStat {

	private String month;
	private double amount;
	private double average;

	public MonthAmountStat(String month, double amount) {
		this.month = month;
		this.amount = amount;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}
}
