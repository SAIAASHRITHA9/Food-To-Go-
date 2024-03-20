package com.concentrix.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.concentrix.project.exception.CartException;
import com.concentrix.project.exception.CustomerException;
import com.concentrix.project.exception.ItemException;
import com.concentrix.project.exception.RestaurantException;
import com.concentrix.project.model.Cart;
import com.concentrix.project.model.Customer;
import com.concentrix.project.model.Item;
import com.concentrix.project.model.Restaurant;
import com.concentrix.project.service.CartService;
import com.concentrix.project.service.CustomerService;
import com.concentrix.project.service.ItemService;
import com.concentrix.project.service.RestaurantService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	
	private static final Logger logs = LogManager.getLogger("CartController.class");

	@Autowired
	CartService cartService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	RestaurantService restaurantService;
	
	@PostMapping("/addToCart")
	public String addCart(@RequestParam int itemId, @RequestParam int itemQuantity, HttpSession httpSession) throws CustomerException, ItemException, CartException {
	
		if (httpSession.getAttribute("id") != null) {
			
			
			Item item = itemService.getItemById(itemId);
			item.setItemQuantity(itemQuantity);
			itemService.saveItem(item);
			Cart cart = cartService.getCartByCustometId((int) httpSession.getAttribute("id"));
			
			if(item.isAvailablity() == true) {
				logs.info("The item {} is available ", item.getFoodItemName());
				if(cart != null) {
					
					cart.getCartItems().add(item);
					cartService.saveCart(cart);
					logs.info("The item {} is added to cart", item.getFoodItemName());
				}
				else {
					
					Customer customer = customerService.getCustomerById((int) httpSession.getAttribute("id"));
					List<Item> cartList = new ArrayList<Item>();
					cartList.add(item);
					
					cartService.saveCart(customer,  cartList);
				}
				logs.info("The cart page is opened");
				return "redirect:/showCart";		
			}
			else {
				
				logs.info("The item {} is not available", item.getFoodItemName());
				return "itemNotAvailable";
			}
			
		}
		else {
			
			logs.info("The customer must be loogined to view the cart");
			return "redirect:/customerLogin";
		}
	}
	
	
	@GetMapping("/showCart")
	public String viewCart(HttpSession httpSession, Model model) throws CustomerException, CartException, RestaurantException {
		
		int customerId = (int) httpSession.getAttribute("id");
		Cart customerCart = cartService.getCartByCustometId(customerId);
		if(customerCart != null) {
		Map<Item, Restaurant> cartList =  new HashMap<Item, Restaurant>();
		List<Item> itemList = customerCart.getCartItems();
		for(Item item : customerCart.getCartItems()) {
			
			Restaurant restaurant = restaurantService.getRestaurantByItemId(item.getItemId());
			cartList.put(item, restaurant);
		}
		model.addAttribute("itemList", itemList);
		model.addAttribute("cartList", cartList);
		
		logs.info("The customer cart is opened");
		return "cart";
		}
		return "cart";
		
	}
	
	
	@GetMapping("/deleteFromCart/{id}")
	public String deleteCart(@PathVariable(value = "id") int id,HttpSession session) throws CartException, CustomerException, ItemException{
		
		int customerId = (int) session.getAttribute("id");
		Cart cart = cartService.getCartByCustometId(customerId);
		int cartId = cart.getCartId();
		
		cartService.deleteItemFromCart(cartId, id);
		
		logs.info("The item id {} from the cart with id {} is being removed", id, cartId);
		return "redirect:/showCart";
	}
	
	
}
