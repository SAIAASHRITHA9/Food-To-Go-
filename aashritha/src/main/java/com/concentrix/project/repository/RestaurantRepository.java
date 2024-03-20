package com.concentrix.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concentrix.project.exception.RestaurantException;
import com.concentrix.project.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{

	Restaurant getByRestaurantEmail(String restaurantEmail) throws RestaurantException ;
	
}
