package com.concentrix.project.service;

import java.time.LocalDateTime;
import java.util.List;

import com.concentrix.project.exception.OrdersException;
import com.concentrix.project.model.Customer;
import com.concentrix.project.model.Item;
import com.concentrix.project.model.Orders;
import com.concentrix.project.model.Restaurant;

public interface OrdersService {

	void saveOrder(LocalDateTime orderDate, String orderStatus, Customer customer, Item item, Restaurant  itemRestaurant);

	List<Orders> getAllOrderFromCustomerId(int customerId) throws OrdersException;

	List<Orders> getOrdersByRestaurantId(int restaurantId) throws OrdersException;

	Orders getOrderById(int id)throws OrdersException;

	void updateOrderStatus(Orders order) throws OrdersException;

}
