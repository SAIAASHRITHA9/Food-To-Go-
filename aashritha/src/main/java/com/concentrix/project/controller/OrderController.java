package com.concentrix.project.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.concentrix.project.exception.CartException;
import com.concentrix.project.exception.CustomerException;
import com.concentrix.project.exception.ItemException;
import com.concentrix.project.exception.OrdersException;
import com.concentrix.project.exception.RestaurantException;
import com.concentrix.project.model.Cart;
import com.concentrix.project.model.Customer;
import com.concentrix.project.model.Item;
import com.concentrix.project.model.Orders;
import com.concentrix.project.model.Restaurant;
import com.concentrix.project.service.CartService;
import com.concentrix.project.service.CustomerService;
import com.concentrix.project.service.OrdersService;
import com.concentrix.project.service.RestaurantService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

	private static final Logger logs = LogManager.getLogger("OrderController.class");
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CartService cartService;
	
	@Autowired
	OrdersService ordersService;
	
	@Autowired
	RestaurantService restaurantService;


	@GetMapping("/proceed")
	public String order(HttpSession httpSession, Model model) throws CartException, OrdersException, CustomerException, RestaurantException{
		
		Customer customer = customerService.getCustomerById((int) httpSession.getAttribute("id"));
		Cart cart = cartService.getCartByCustometId(customer.getCustomerId());
		LocalDateTime orderDate = LocalDateTime.now();
		List<Item> cartList = cart.getCartItems();
		if (!(cartList.isEmpty())){
		
		double totalPrice = 0;
		for(Item item : cartList) {
			
			totalPrice += item.getPrice() * item.getItemQuantity();
			Restaurant itemRestaurant = restaurantService.getRestaurantByItemId(item.getItemId());
			String orderStatus = "Accepted";
			ordersService.saveOrder(orderDate, orderStatus, customer, item,  itemRestaurant);
		}
		
		String address = customer.getCustomerAddress();
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("address", address);
		
		logs.info("Customer is at the payment page with total cost {} and delivered to address {}", totalPrice, address);
		return "payment";
		}
		else {
			
			throw new OrdersException(); 
		}
	}
	
	@GetMapping("/placeOrder")
	public String placedOrder( HttpSession httpSession) throws CartException, CustomerException {
		
		int customerId = (int) httpSession.getAttribute("id");
		Cart cart = cartService.getCartByCustometId(customerId);

		cart.getCartItems().clear();
		cartService.saveCart(cart);
		
		logs.info("The customer has placed the order successfully");
		return "placeOrder";
	}
	
	@GetMapping("/customerOrders")
	public String showOrder(HttpSession httpSession, Model model) throws CustomerException, CartException, RestaurantException, OrdersException {
		
		int customerId = (int) httpSession.getAttribute("id");
		List<Orders> customerOrderDetails = ordersService.getAllOrderFromCustomerId(customerId);
		model.addAttribute("customerOrderDetails", customerOrderDetails);
		
		logs.info("Customer is at previous orders page");
		return "customerOrders";
	}
	
	
	@GetMapping("/manageOrderDetails")
	public String orderHistory(HttpSession session, Model model) throws RestaurantException, ItemException, OrdersException {
		
		int restaurantId = (int) session.getAttribute("restaurantId");
		List<Orders> orderList = ordersService.getOrdersByRestaurantId(restaurantId);
		model.addAttribute("orderList", orderList);
		
		logs.info("Restaurant owner with id {} is at order details page", restaurantId);
		return "orderHistory";
	}
	
	@GetMapping("/updateOrderStatus/{id}")
	public String updateOrderStatus( @PathVariable (value = "id") int id, Model model) throws OrdersException {
		
		Orders order = ordersService.getOrderById(id);
		model.addAttribute("order", order);
		
		logs.info("Restaurant owner wants to update the order {}", order);
		return "updateOrder";
	}
	
	@PostMapping("/saveUpdateStatus")
	public String updatedStatus(@ModelAttribute Orders order, HttpSession session) throws ItemException, RestaurantException, OrdersException {
			
			ordersService.updateOrderStatus(order);
			
			logs.info("Restaurant owner updated the order {}", order);
			return "redirect:/manageOrderDetails";
	}
	
}
