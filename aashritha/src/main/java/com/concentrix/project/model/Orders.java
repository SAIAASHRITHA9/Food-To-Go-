package com.concentrix.project.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int orderId;
	
	private LocalDateTime orderDate;
	
	private String orderStatus;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Item orderedItem;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Restaurant restaurant;
	
	
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Orders(int orderId, LocalDateTime orderDate, String orderStatus, Customer customer, Item orderedItem,
			Restaurant restaurant) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.customer = customer;
		this.orderedItem = orderedItem;
		this.restaurant = restaurant;
	}


	public Orders(LocalDateTime orderDate, String orderStatus, Customer customer, Item orderedItem,
			Restaurant restaurant) {
		super();
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.customer = customer;
		this.orderedItem = orderedItem;
		this.restaurant = restaurant;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public LocalDateTime getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Item getOrderedItem() {
		return orderedItem;
	}


	public void setOrderedItem(Item orderedItem) {
		this.orderedItem = orderedItem;
	}


	public Restaurant getRestaurant() {
		return restaurant;
	}


	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}


	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus
				+ ", customer=" + customer + ", orderedItem=" + orderedItem + ", restaurant=" + restaurant + "]";
	}

}
