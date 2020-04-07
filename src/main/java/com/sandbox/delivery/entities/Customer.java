package com.sandbox.delivery.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Customer{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idCustomer;

	private String customerNumber;

	@OneToMany (cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE} ,fetch = FetchType.EAGER)
	@JoinTable(name = "address_customer", 
		joinColumns = @JoinColumn(name = "id_customer"), 
		inverseJoinColumns = @JoinColumn(name = "id_address"))
	private List<Address> customerListDeliveryAddress = new ArrayList<>();

	private String phone;
	private String contactName;
	private boolean arragement;

	public Customer() {
	}

	/**
	 * @param customerNumber
	 * @param customerListDeliveryAddress
	 * @param phone
	 * @param contactName
	 * @param arragement
	 */
	public Customer(String customerNumber, List<Address> customerListDeliveryAddress, String phone, String contactName,
			boolean arragement) {
		super();
		this.customerNumber = customerNumber;
		this.customerListDeliveryAddress = customerListDeliveryAddress;
		this.phone = phone;
		this.contactName = contactName;
		this.arragement = arragement;
	}

	public long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(long id) {
		this.idCustomer = id;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public List<Address> getCustomerListDeliveryAddress() {
		return customerListDeliveryAddress;
	}

	public void setCustomerListDeliveryAddress(List<Address> customerListDeliveryAddress) {
		this.customerListDeliveryAddress = customerListDeliveryAddress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public boolean isArragement() {
		return arragement;
	}

	public void setArragement(boolean arragement) {
		this.arragement = arragement;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customerNumber == null) ? 0 : customerNumber.hashCode());
		result = prime * result + (int) (idCustomer ^ (idCustomer >>> 32));
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
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
		Customer other = (Customer) obj;
		if (customerNumber == null) {
			if (other.customerNumber != null)
				return false;
		} else if (!customerNumber.equals(other.customerNumber))
			return false;
		if (idCustomer != other.idCustomer)
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

}
