package com.sandbox.delivery.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long idAdress;

	private String streetOne;
	private String streetTwo;
	private String streetThree;
	private String zipCode;
	private String city;
	private boolean floor;

	public Address() {
	}

	/**
	 * @param streetOne
	 * @param streetTwo
	 * @param streetThree
	 * @param zipCode
	 * @param city
	 * @param floor
	 */
	public Address(String streetOne, String streetTwo, String streetThree, String zipCode, String city, boolean floor) {
		super();
		this.streetOne = streetOne;
		this.streetTwo = streetTwo;
		this.streetThree = streetThree;
		this.zipCode = zipCode;
		this.city = city;
		this.floor = floor;
	}

	public long getIdAddress() {
		return idAdress;
	}

	public void setIdAddress(long id) {
		this.idAdress = id;
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

	public String getStreetThree() {
		return streetThree;
	}

	public void setStreetThree(String streetThree) {
		this.streetThree = streetThree;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isFloor() {
		return floor;
	}

	public void setFloor(boolean floor) {
		this.floor = floor;
	}

}
