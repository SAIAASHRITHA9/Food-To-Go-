package com.concentrix.project.controller;


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

import com.concentrix.project.exception.ItemException;
import com.concentrix.project.exception.RestaurantException;
import com.concentrix.project.model.Item;
import com.concentrix.project.model.Restaurant;
import com.concentrix.project.service.ItemService;
import com.concentrix.project.service.RestaurantService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ItemController {

	private static final Logger logs = LogManager.getLogger("ItemController.class");
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	RestaurantService restaurantService;
	
	@GetMapping("/viewItems")
	public String restaurantAllFoodItem(HttpSession sesssion, Model model) throws RestaurantException {
		
		Restaurant restaurant = restaurantService.getRestaurantById((int) sesssion.getAttribute("restaurantId"));
		List<Item> listItem =  restaurant.getItems();
		model.addAttribute("listItem", listItem);
		
		logs.info("The {} restaurant food items are being displayed", restaurant.getRestaurantName());
		return "restaurantFoodItems";
	}
	
	@GetMapping("/addItem")
	public String addItem(Model model) {
		
		Item item = new Item();
		model.addAttribute("item", item);
		
		logs.info("The restaurant wants to add food item");
		return "addItem";
	}
	
	
	@PostMapping("/saveItem")
	public String saveItem(@ModelAttribute Item item, HttpSession session) throws ItemException, RestaurantException {
		
		Restaurant restaurant = restaurantService.getRestaurantById((int) session.getAttribute("restaurantId"));
		itemService.saveItem(item);
		restaurant.getItems().add(item);
		restaurantService.saveRestaurant(restaurant);
		
		logs.info("The {} restaurant added food items {}",restaurant.getRestaurantName(), item.getFoodItemName() );
		return "redirect:/manageFoodItems";
	}
	
	@GetMapping("/updateFoodItem/{id}")
	public String updateFoodItem(@PathVariable (value = "id") int id, Model model) throws ItemException {
		
		Item item = itemService.getItemById(id);
		model.addAttribute("item", item);
		
		logs.info("The restaurant wants to update the food items {} ", item.getFoodItemName());
		return "updateItem";
	}

	@PostMapping("/saveUpdateItem")
	public String saveUpdateItem(@ModelAttribute Item item, HttpSession session) throws ItemException, RestaurantException {
		
		itemService.saveItem(item);
		
		logs.info("The restaurant  updated the food items {} ", item.getFoodItemName());
		return "redirect:/viewItems";
	}
	
	@GetMapping("/deleteFoodItem/{id}")
	public String deleteItem(@PathVariable (value = "id") int id, HttpSession session) throws ItemException, RestaurantException {
		
		int restaurantId = (int) session.getAttribute("restaurantId");
		Item item = itemService.getItemById(id);
		restaurantService.deleteItemFromRestauarantByItemId(restaurantId, item);
		itemService.deleteItemById(id);
		
		logs.info("The restaurant wants to delete the food items {} ", item.getFoodItemName());
		return "redirect:/viewItems";
	}

	
}
