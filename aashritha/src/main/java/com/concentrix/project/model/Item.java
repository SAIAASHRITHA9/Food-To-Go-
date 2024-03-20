package com.concentrix.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Item {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int itemId;
	
	private  String foodItemName;
	private boolean availablity;
	private double price;
	private int itemQuantity;
	private String url;
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Item(int itemId, String foodItemName, boolean availablity, double price, String url) {
		super();
		this.itemId = itemId;
		this.foodItemName = foodItemName;
		this.availablity = availablity;
		this.price = price;
		this.setUrl(url);
	}

	public Item(String foodItemName, boolean availablity, double price, String url) {
		super();
		this.foodItemName = foodItemName;
		this.availablity = availablity;
		this.price = price;
		this.setUrl(url);
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getFoodItemName() {
		return foodItemName;
	}

	public void setFoodItemName(String foodItemName) {
		this.foodItemName = foodItemName;
	}

	public boolean isAvailablity() {
		return availablity;
	}

	public void setAvailablity(boolean availablity) {
		this.availablity = availablity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int quantity) {
		this.itemQuantity = quantity;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", foodItemName=" + foodItemName + ", availablity=" + availablity + ", price="
				+ price + ", itemQuantity=" + itemQuantity + ", url=" + url + "]";
	}

	
}
