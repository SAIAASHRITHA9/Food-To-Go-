package com.concentrix.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concentrix.project.exception.RestaurantException;
import com.concentrix.project.model.Item;
import com.concentrix.project.model.Restaurant;
import com.concentrix.project.repository.RestaurantRepository;

@Service
public class RestaurantServiceImp implements RestaurantService {
	
	@Autowired
	RestaurantRepository restaurantRepository;

	@Override
	public Restaurant getByRestaurantEmail(String restaurantEmail) throws RestaurantException {
		
		return this.restaurantRepository.getByRestaurantEmail(restaurantEmail);
	}

	@Override
	public Restaurant saveRestaurant(Restaurant restaurant) throws RestaurantException {
		// TODO Auto-generated method stub
		return this.restaurantRepository.save(restaurant);
	}

	@Override
	public Restaurant getRestaurantById(int restaurantId) throws RestaurantException {
		// TODO Auto-generated method stub
		Optional<Restaurant> optional = restaurantRepository.findById(restaurantId);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new RestaurantException("The restaurant id not found");
		}
		
	}

	@Override
	public void deleteItemFromRestauarantByItemId(int restaurantId, Item item) throws RestaurantException {
		
		Optional<Restaurant> optional  = restaurantRepository.findById(restaurantId);
		
		if(optional.isPresent()) {
			
			optional.get().getItems().remove(item);
			restaurantRepository.save(optional.get());
			
		}
		else {
			
			throw new RestaurantException("The restaurant id not found");
		}
		
	}

	@Override
	public Map<Restaurant, Item> getRestaurantHavingFoodItem(String foodItemName) throws RestaurantException {
		
		List<Restaurant> allRestaurants = restaurantRepository.findAll();
		Map<Restaurant, Item> restaurantHavingFoodItem = new HashMap<Restaurant, Item>();
		for(Restaurant restaurant : allRestaurants) {
			
			List<Item> itemList = restaurant.getItems();
			
			for(Item item : itemList) {
				
				String name = item.getFoodItemName();
				name = name.replaceAll(" ", "");

			
				if(item.getFoodItemName().equalsIgnoreCase(foodItemName) || (name.equalsIgnoreCase(foodItemName))) {
					
					restaurantHavingFoodItem.put(restaurant, item);
				}
			}
		}
		return restaurantHavingFoodItem;
	}

	@Override
	public Restaurant getByRestaurantName(String restaurantName) throws RestaurantException {
	
		List<Restaurant> allRestaurants = restaurantRepository.findAll();
		Restaurant restaurant = null;
		for(Restaurant res : allRestaurants) {
			
			String name = res.getRestaurantName().replaceAll("\\s", "");
			if(res.getRestaurantName().equalsIgnoreCase(restaurantName)|| (name.equalsIgnoreCase(restaurantName))) {
				
				restaurant = res;
			}
		}
		return restaurant;
	}

	@Override
	public Restaurant getRestaurantByItemId(int itemId) {

		List<Restaurant> allRestaurants = restaurantRepository.findAll();
		
		for(Restaurant res : allRestaurants) {
			
			for(Item item : res.getItems()) {
				
				if(item.getItemId() == itemId) {
					
					return res;
				}
			}
		}
		return null;	
	}

	@Override
	public Map<Item, Restaurant> getRestaurantNotHavingFoodItem(String foodItemName) throws RestaurantException {
		List<Restaurant> allRestaurants = restaurantRepository.findAll();
		Map<Item, Restaurant> restaurantNotHavingFoodItem = new HashMap<Item, Restaurant>();
		for(Restaurant restaurant : allRestaurants) {
			
			List<Item> itemList = restaurant.getItems();
			
			for(Item item : itemList) {
			
				if (!(item.getFoodItemName().equalsIgnoreCase(foodItemName))) {
					
					restaurantNotHavingFoodItem.put(item, restaurant);
				}
			}
		}
		return restaurantNotHavingFoodItem;
	}


}
