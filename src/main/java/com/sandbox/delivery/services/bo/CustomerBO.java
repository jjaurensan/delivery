package com.sandbox.delivery.services.bo;

import java.util.ArrayList;
import java.util.List;

public class CustomerBO {
	private long idCustomer;

	private String customerNumber;
	private List<AddressBO> customerListDeliveryAddress = new ArrayList<>();

	private String phone;
	private String contactName;
	private boolean arragement;

	public CustomerBO() {
	}

	public CustomerBO(String customerNumber, List<AddressBO> customerListDeliveryAddress, String phone,
			String contactName, boolean arragement) {
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

	public List<AddressBO> getCustomerListDeliveryAddress() {
		return customerListDeliveryAddress;
	}

	public void setCustomerListDeliveryAddress(List<AddressBO> customerListDeliveryAddress) {
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
		result = prime * result + (arragement ? 1231 : 1237);
		result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
		result = prime * result + ((customerListDeliveryAddress == null) ? 0 : customerListDeliveryAddress.hashCode());
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
		CustomerBO other = (CustomerBO) obj;
		if (arragement != other.arragement)
			return false;
		if (contactName == null) {
			if (other.contactName != null)
				return false;
		} else if (!contactName.equals(other.contactName)) {
			return false;
		}
		if (customerListDeliveryAddress == null) {
			if (other.customerListDeliveryAddress != null)
				return false;
		} else if (!customerListDeliveryAddress.equals(other.customerListDeliveryAddress)) {
			return false;
		}
		if (customerNumber == null) {
			if (other.customerNumber != null) {
				return false;
			}
		} else if (!customerNumber.equals(other.customerNumber)) {
			return false;
		}

		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone)) {
			return false;
		}
		return true;
	}

}
