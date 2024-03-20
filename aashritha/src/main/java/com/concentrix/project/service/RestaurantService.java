package com.concentrix.project.service;

import java.util.Map;

import com.concentrix.project.exception.RestaurantException;
import com.concentrix.project.model.Item;
import com.concentrix.project.model.Restaurant;

public interface RestaurantService {

	Restaurant getByRestaurantEmail(String restaurantEmail) throws RestaurantException;

	Restaurant saveRestaurant(Restaurant restaurant) throws RestaurantException;

	Restaurant getRestaurantById(int restaurantId) throws RestaurantException;

	void deleteItemFromRestauarantByItemId(int restaurantId, Item item)  throws RestaurantException;

	Map<Restaurant, Item> getRestaurantHavingFoodItem(String foodItemName) throws RestaurantException;

	Restaurant getByRestaurantName(String restaurantName) throws RestaurantException;


	Restaurant getRestaurantByItemId(int itemId) throws RestaurantException;
	
	Map<Item, Restaurant> getRestaurantNotHavingFoodItem(String foodItemName) throws RestaurantException;
	

	

}
