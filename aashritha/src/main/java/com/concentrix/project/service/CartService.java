package com.concentrix.project.service;


import java.util.List;

import com.concentrix.project.exception.CartException;
import com.concentrix.project.model.Cart;
import com.concentrix.project.model.Customer;
import com.concentrix.project.model.Item;

public	interface CartService {

	Cart getCartByCustometId(int customerId) throws CartException;

	void saveCart(Cart cart)throws CartException;

	void saveCart(Customer customer, List<Item> cartList) throws CartException;

	void deleteItemFromCart(int cartId, int id)throws CartException;

	
}
