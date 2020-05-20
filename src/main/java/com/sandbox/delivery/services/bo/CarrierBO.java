package com.sandbox.delivery.services.bo;

import java.io.Serializable;

public class CarrierBO implements Serializable {
	private static final long serialVersionUID = 1L;

	private long idCarrier;

	private String name;
	private String streetOne;
	private String streetTwo;
	private String cityCode;
	private String city;
	private String phone;

	public CarrierBO(String name, String streetOne, String streetTwo, String cityCode, String city, String phone) {
		this.name = name;
		this.phone = phone;
		this.streetOne = streetOne;
		this.streetTwo = streetTwo;
		this.cityCode = cityCode;
		this.city = city;
	}

	public CarrierBO() {
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();		
		builder.append(name);
		builder.append("\n");
		builder.append(streetOne);
		builder.append("\n");
		builder.append(streetTwo);
		builder.append("\n");
		builder.append(cityCode);
		builder.append(" ");
		builder.append(city);
		return builder.toString();
	}

	public long getIdCarrier() {
		return idCarrier;
	}

	public void setIdCarrier(long id) {
		this.idCarrier = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStreetOne() {
		return streetOne;
	}

	public void setStreetOne(String streetOne) {
		this.streetOne = streetOne;
	}

	public String getStreetTwo() {
		return streetTwo;
	}

	public void setStreetTwo(String streetTwo) {
		this.streetTwo = streetTwo;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
