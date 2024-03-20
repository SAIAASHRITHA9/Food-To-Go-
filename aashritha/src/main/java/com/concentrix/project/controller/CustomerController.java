package com.concentrix.project.controller;

import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.concentrix.project.exception.CustomerException;
import com.concentrix.project.exception.ItemException;
import com.concentrix.project.exception.RestaurantException;
import com.concentrix.project.model.Customer;
import com.concentrix.project.model.Item;
import com.concentrix.project.model.Restaurant;
import com.concentrix.project.service.CustomerService;
import com.concentrix.project.service.ItemService;
import com.concentrix.project.service.RestaurantService;


import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {
	
	private static final Logger logs = LogManager.getLogger("CustomerController.class");
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	RestaurantService restaurantService;
	
	@Autowired
	ItemService itemService;
	
	
	@GetMapping("/customerLogin")
	public String customerLogin() {
		
		return "customerLogin";
	}
	
	
	@GetMapping("/customerRegistrationform")
	public String customerRegistration(Model model) {
		
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		
		logs.info("Customer is at registration" );
		return "customerRegistration";
	}
	
	@PostMapping("/customerRegistered")
	public String reDirectToLoginRest(@ModelAttribute("customer") Customer customer, Model model) throws CustomerException  {
		
		if(customer.getCustomerEmail() != null) {
			
			customerService.saveCustomer(customer);
			logs.info("Customer is registered with  details {}", customer);
			return "redirect:/customerLogin";
		}
		else {
			logs.warn("Customer registration is not completed");
			throw new CustomerException("The customer is not registerd.");
		}
	}

	@PostMapping("/customerCheck")
	public String CustomerHomePage(@RequestParam String customerEmail, @RequestParam String customerPassword, HttpSession session, Model model) throws CustomerException{
		
		Customer customer = customerService.getCustomerByEmail(customerEmail);
		if(customer != null && customer.getCustomerPassword().equals(customerPassword)) {
			
			session.setAttribute("id", customer.getCustomerId());
			logs.info("Customer has logined with email id {} and password {}", customerEmail, customerPassword);
			return "customerHome";
		}
		else {
			
			logs.warn("Login is unsuccessful");
			throw new CustomerException("check username and password");
		}
	}
	
	@PostMapping("/searchItem")
	public String searchItem(@RequestParam String foodItemName, Model model) throws RestaurantException {
		
		Map<Restaurant, Item> restaurantHavingFoodItem = restaurantService.getRestaurantHavingFoodItem(foodItemName);
		model.addAttribute("restaurantHavingFoodItem" ,restaurantHavingFoodItem);
		

		Map<Item, Restaurant> restaurantNotHavingFoodItem = restaurantService.getRestaurantNotHavingFoodItem(foodItemName);
		model.addAttribute("restaurantNotHavingFoodItem" ,restaurantNotHavingFoodItem);
		
		
		logs.info("Customer has searched for food item {}", foodItemName);
		return "itemListByFoodName";
	}
	

	
	@PostMapping("/viewRestaurantItems")
	public String viewRestaurantItems(@RequestParam String foodItemName, @RequestParam int restaurantId, Model model) throws RestaurantException {
		
		Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
		List<Item> listItem = restaurant.getItems();
		
		System.out.println(listItem + "********************************************************************");
		model.addAttribute("listItem", listItem);
		model.addAttribute("foodItemName", foodItemName);
		
		logs.info("Customer choose the item from the restaurant {}", restaurant.getRestaurantName());
		return "restaurantItemListByCustomer";
		
	}
	@PostMapping("/searchRestaurant")
	public String searchRestaurant(@RequestParam String restaurantName, Model model) throws  RestaurantException {
		
		Restaurant restaurant = restaurantService.getByRestaurantName(restaurantName);
		List<Item> listItem = restaurant.getItems();
		model.addAttribute("listItem", listItem);
		
		logs.info("Customer has searched for restaurant {}", restaurantName);
		return "itemListByRestaurant";
		
	}
	
	@GetMapping("/showCustomerHome")
	public String backToHome(HttpSession httpSession) {
		if(httpSession.getAttribute("id") == null) {
			
			return "home";
		}
		
		logs.info("The customer is back too home page");
		return "customerHome";
	}
	
	@PostMapping("/showBackPage")
	public String back(@RequestParam String itemName, Model model) throws RestaurantException, ItemException {
		
		
		Map<Restaurant, Item> restaurantHavingFoodItem = restaurantService.getRestaurantHavingFoodItem(itemName);
		model.addAttribute("restaurantHavingFoodItem" ,restaurantHavingFoodItem);
		

		Map<Item, Restaurant> restaurantNotHavingFoodItem = restaurantService.getRestaurantNotHavingFoodItem(itemName);
		model.addAttribute("restaurantNotHavingFoodItem" ,restaurantNotHavingFoodItem);
		
		logs.info("Customer has searched for food item {}", itemName);
		return "itemListByFoodName";
	}
	
	
	
	@GetMapping("/logoutcus")
	public String log(HttpSession session) {
		session.invalidate();
		
		logs.info("customer is logging out");
		return "home";
	}
	
	@GetMapping("/viewCustomerProfile")
	public String viewCustomerProfile(HttpSession session, Model model) throws CustomerException {
		
		int customerId = (int) session.getAttribute("id");
		Customer customer = customerService.getCustomerById(customerId);
		model.addAttribute("customer",customer);
		
		logs.info("customer is at  profile page");
		return "customerProfile";
	}
	
	@GetMapping("/updateCustomerForm")
	public String updateCustomerForm(HttpSession session, Model model) throws CustomerException {
		
		int customerId = (int)session.getAttribute("id");
		Customer customer = customerService.getCustomerById(customerId);
		model.addAttribute("customer", customer);
		
		logs.info("customer wants to update profile");
		return "updateCustomer";
	}
	
	@PostMapping("/saveUpdateCustomer")
	public String saveUpdateCustomer(@ModelAttribute Customer customer,HttpSession session) throws CustomerException {
		
		customerService.saveCustomer(customer);
		
		logs.info("customer updated profile");
		return "redirect:/viewCustomerProfile";
	}

}
