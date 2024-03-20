package com.concentrix.project.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int cartId;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
	
	
	@OneToMany(targetEntity = Item.class, cascade = CascadeType.ALL)
	private List<Item> cartItems;
	

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Cart(int cartId, Customer customer, List<Item> cartItems) {
		super();
		this.cartId = cartId;
		this.customer = customer;
		this.cartItems = cartItems;
	}


	public Cart(Customer customer, List<Item> cartItems) {
		super();
		this.customer = customer;
		this.cartItems = cartItems;
	}


	public int getCartId() {
		return cartId;
	}


	public void setCartId(int cartId) {
		this.cartId = cartId;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public List<Item> getCartItems() {
		return cartItems;
	}


	public void setCartItems(List<Item> cartItems) {
		this.cartItems = cartItems;
	}


	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", customer=" + customer  + ", cartItems="
				+ cartItems + "]";
	}
	
}
