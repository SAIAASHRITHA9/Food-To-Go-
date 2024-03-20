package com.concentrix.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concentrix.project.exception.CartException;
import com.concentrix.project.model.Cart;
import com.concentrix.project.model.Customer;
import com.concentrix.project.model.Item;
import com.concentrix.project.repository.CartRepository;

@Service
public class CartServiceImp implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Override
	public Cart getCartByCustometId(int customerId) throws CartException {
		
		List<Cart> allCarts = cartRepository.findAll();
		for(Cart cart : allCarts) {
			
			if(cart.getCustomer().getCustomerId() == customerId) {
				
				return cart;
			}
		}
		return null;
	}

	@Override
	public void saveCart(Cart cart) throws CartException {
		// TODO Auto-generated method stub
		this.cartRepository.save(cart);
	}

	@Override
	public void saveCart(Customer customer, List<Item> cartList) throws CartException {
		// TODO Auto-generated method stub
		Cart cart = new Cart(customer, cartList);
		this.cartRepository.save(cart);
	}

	@Override
	public void deleteItemFromCart(int cartId, int id) throws CartException {
		
		Optional<Cart> optionalCart = cartRepository.findById(cartId);
		if(optionalCart.isPresent()) {
			
			Cart cart = optionalCart.get();
			List<Item> items = cart.getCartItems();
			items.removeIf(item -> item.getItemId() == (id));
			cartRepository.save(cart);
		}
		else {
			
			throw new CartException("Cart Empty");
		}
		
		
	}
	


}
