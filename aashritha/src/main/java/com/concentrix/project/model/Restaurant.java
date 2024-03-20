package com.concentrix.project.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int restaurantId;
	
	private  String restaurantName;
	private String restaurantOwnerName;
	
	@Column(unique = true)
	private String restaurantEmail;
	private String restaurantPassword;
	private String restaurantPhoneNumber;
	
	
	private String address;
	
	private String url;
	
	@OneToMany(targetEntity = Item.class, cascade = CascadeType.ALL)
	private List<Item> items;

	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Restaurant(int restaurantId, String restaurantName, String restaurantOwnerName, String restaurantEmail,
			String restaurantPassword, String restaurantPhoneNumber, String address, List<Item> items, String url) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.restaurantOwnerName =restaurantOwnerName;
		this.restaurantEmail = restaurantEmail;
		this.restaurantPassword = restaurantPassword;
		this.restaurantPhoneNumber = restaurantPhoneNumber;
		this.address = address;
		this.items = items;
		this.url = url;
	}

	public Restaurant(String restaurantName, String restaurantOwnerName, String restaurantEmail, String restaurantPassword,
			String restaurantPhoneNumber, String address, List<Item> items, String url) {
		super();
		this.restaurantName = restaurantName;
		this.restaurantOwnerName =restaurantOwnerName;
		this.restaurantEmail = restaurantEmail;
		this.restaurantPassword = restaurantPassword;
		this.restaurantPhoneNumber = restaurantPhoneNumber;
		this.address = address;
		this.items = items;
		this.url = url;
	}
	
	

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getRestaurantOwnerName() {
		return restaurantOwnerName;
	}

	public void setRestaurantOwnerName(String restaurantOwnerName) {
		this.restaurantOwnerName =restaurantOwnerName;
	}

	public String getRestaurantEmail() {
		return restaurantEmail;
	}

	public void setRestaurantEmail(String restaurantEmail) {
		this.restaurantEmail = restaurantEmail;
	}

	public String getRestaurantPassword() {
		return restaurantPassword;
	}

	public void setRestaurantPassword(String restaurantPassword) {
		this.restaurantPassword = restaurantPassword;
	}

	public String getRestaurantPhoneNumber() {
		return restaurantPhoneNumber;
	}

	public void setRestaurantPhoneNumber(String restaurantPhoneNumber) {
		this.restaurantPhoneNumber = restaurantPhoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Restaurant [restaurantId=" + restaurantId + ", restaurantName=" + restaurantName
				+ ", restaurantOwnerName=" + restaurantOwnerName + ", restaurantEmail=" + restaurantEmail
				+ ", restaurantPassword=" + restaurantPassword + ", restaurantPhoneNumber=" + restaurantPhoneNumber
				+ ", address=" + address + ", url=" + url + ", items=" + items + "]";
	}

	
}
