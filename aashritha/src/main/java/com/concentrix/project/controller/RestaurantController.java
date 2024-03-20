package com.concentrix.project.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.concentrix.project.exception.RestaurantException;
import com.concentrix.project.model.Restaurant;
import com.concentrix.project.service.RestaurantService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class RestaurantController {
	
	private static final Logger logs = LogManager.getLogger("RestaurantController.class");
	
	@Autowired
	RestaurantService restaurantService;
	
	@GetMapping("/restaurantLogin")
	public String restaurantLogin() {
		
		return "restaurantLogin";
	}
	
	@GetMapping("/restaurantRegistrationform")
	public String restaurantRegistration(Model model) {
		
		Restaurant restaurant = new Restaurant();
		model.addAttribute("restaurant", restaurant);
		
		logs.info("The owner is registering the restaurant");
		return "restaurantRegistration";
	}
	
	@PostMapping("/restaurantRegistered")
	public String reDirectToLoginRest(@ModelAttribute("restaurant") Restaurant restaurant, Model model) throws RestaurantException  {
		
		if(restaurant.getRestaurantEmail() != null) {
			
			restaurantService.saveRestaurant(restaurant);
			
			logs.info("The restaurant is registered with details {}", restaurant);
			return "redirect:/restaurantLogin";
		}
		else {
			
			logs.warn("Registration is not completed");
			throw new RestaurantException("The restaurant is not registerd.");
		}
	}
	
	@PostMapping("/restaurantCheck")
	public String restaurantHome(@RequestParam String restaurantEmail, @RequestParam String restaurantPassword, HttpServletRequest req, Model model) throws RestaurantException {
		Restaurant restaurant = restaurantService.getByRestaurantEmail(restaurantEmail);
		if(restaurant != null && restaurant.getRestaurantPassword().equals(restaurantPassword)) {
			
			HttpSession session = req.getSession();
			session.setAttribute("restaurantId", restaurant.getRestaurantId());
			
			logs.info("The owner has loginned with email {} and password {}", restaurantEmail, restaurantPassword);
			return "restaurantHome";
		}
		else {
			
			logs.warn("There is a logging issue");
			return "redirect:/restaurantLogin";
		}
	}
	
	@GetMapping("/manageFoodItems")
	public String restaurantManage() {
		
		logs.info("The owner is managing food items in the restaurant");
		return "restaurantFoodManage";
	}
	
	@GetMapping("/backToRestaurantHome")
	public String returnRestaurantHome() {
		
		logs.info("The owner is back to home page");
		return "restaurantHome";
	}
	
	@GetMapping("/logoutRestaurant")
	public String logoutRestaurant(HttpSession session) {
		
		session.invalidate();
		logs.info("The owner logged out successfully");
		return "home";
	}
	
	@GetMapping("/viewRestaurantProfile")
	public String viewCustomerProfile(HttpSession session, Model model) throws RestaurantException {
		
		int restaurantId = (int) session.getAttribute("restaurantId");
		Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
		model.addAttribute("restaurant",restaurant);
		
		logs.info("The owner is at profile page");
		return "restaurantProfile";
	}
	@GetMapping("/updateRestaurantForm")
	public String updateCustomerForm(HttpSession session, Model model) throws RestaurantException {
		
		int restaurantId = (int) session.getAttribute("restaurantId");
		Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
		model.addAttribute("restaurant",restaurant);
		
		logs.info("The owner wants to update the profile");
		return "updateRestaurant";
	}
	
	@PostMapping("/saveUpdateRestaurant")
	public String saveUpdateCustomer(@ModelAttribute Restaurant restaurant) throws RestaurantException {
		restaurantService.saveRestaurant(restaurant);
		
		logs.info("The owner updated the profile");
		return "redirect:/viewRestaurantProfile";
	}

}
