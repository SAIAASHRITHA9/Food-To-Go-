package com.concentrix.project.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int customerId;
	
	private  String customerName;
	
	@Column(unique = true)
	private String customerEmail;
	
	private String customerPassword;
	private String customerPhoneNumber;
	

	private String customerAddress;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cart cart;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int customerId, String customerName, String customerEmail, String customerPassword,
			String customerPhoneNumber, String customerAddress, Cart cart) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerPassword = customerPassword;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerAddress = customerAddress;
		this.cart = cart;
	}

	public Customer(String customerName, String customerEmail, String customerPassword, String customerPhoneNumber,
			String customerAddress, Cart cart) {
		super();
		this.customerName = customerName;
		this.customerEmail = customerEmail;
		this.customerPassword = customerPassword;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerAddress = customerAddress;
		this.cart = cart;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerPassword() {
		return customerPassword;
	}

	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerName=" + customerName + ", customerEmail="
				+ customerEmail + ", customerPassword=" + customerPassword + ", customerPhoneNumber="
				+ customerPhoneNumber + ", customerAddress=" + customerAddress + ", cart=" + cart + "]";
	}
	
	
}
