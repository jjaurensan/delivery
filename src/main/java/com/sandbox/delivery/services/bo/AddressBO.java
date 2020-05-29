package com.sandbox.delivery.services.bo;

public class AddressBO {

	private long idAddress;
	private String streetOne;
	private String streetTwo;
	private String streetThree;
	private String zipCode;
	private String city;
	private boolean floor;

	public AddressBO() {
	}

	public AddressBO(String streetOne, String streetTwo, String streetThree, String zipCode, String city,
			boolean floor) {
		super();
		this.streetOne = streetOne;
		this.streetTwo = streetTwo;
		this.streetThree = streetThree;
		this.zipCode = zipCode;
		this.city = city;
		this.floor = floor;
	}

	@Override
	public String toString() {
		final String BREAK_LINE = "\n";
		StringBuilder builder = new StringBuilder();

		builder.append(streetOne);

		if (streetTwo != null || !streetTwo.isBlank()) {
			builder.append(BREAK_LINE);
			builder.append(streetTwo);
		}
		if (streetThree != null || !streetThree.isBlank()) {
			builder.append(BREAK_LINE);
			builder.append(streetThree);
		}
		builder.append(BREAK_LINE);
		builder.append(zipCode);
		builder.append(" ");
		builder.append(city);

		return builder.toString();
	}

	public long getIdAddress() {
		return idAddress;
	}

	public void setIdAddress(long id) {
		this.idAddress = id;
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
