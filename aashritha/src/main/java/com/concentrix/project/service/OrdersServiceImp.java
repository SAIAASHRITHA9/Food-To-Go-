package com.concentrix.project.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concentrix.project.exception.OrdersException;
import com.concentrix.project.model.Customer;
import com.concentrix.project.model.Item;
import com.concentrix.project.model.Orders;
import com.concentrix.project.model.Restaurant;
import com.concentrix.project.repository.OrdersRepository;


@Service
public class OrdersServiceImp implements OrdersService {

	@Autowired
	OrdersRepository ordersRepository;

	@Override
	public void saveOrder(LocalDateTime orderDate, String orderStatus, Customer customer, Item item,  Restaurant  itemRestaurant) {
		// TODO Auto-generated method stub
		Orders order = new Orders(orderDate, orderStatus, customer, item, itemRestaurant);
		this.ordersRepository.save(order);
		
	}

	@Override
	public List<Orders> getAllOrderFromCustomerId(int customerId) {
		
		List<Orders> allOrders = ordersRepository.findAll();
		List<Orders> customerOrders = new ArrayList<Orders>();
		for(Orders order : allOrders) {
			
			if(order.getCustomer().getCustomerId() == customerId) {
				
				customerOrders.add(order);
			}
		}
		return customerOrders;
	}

	@Override
	public List<Orders> getOrdersByRestaurantId(int restaurantId) {
		// TODO Auto-generated method stub
		List<Orders> allOrders = ordersRepository.findAll();
		List<Orders> ordersFromRestaurant = new ArrayList<Orders>();
		for(Orders order : allOrders) {
			
			if(order.getRestaurant().getRestaurantId() == restaurantId) {
				
				ordersFromRestaurant.add(order);
			}
		}
		return ordersFromRestaurant;
	}

	@Override
	public Orders getOrderById(int id) {
		
		Optional<Orders> optionalOrder = ordersRepository.findById(id);
		Orders order = null;
		if(optionalOrder.isPresent()) {
			
			order = optionalOrder.get();
		}
		return order;
	}

	@Override
	public void updateOrderStatus(Orders order) throws OrdersException {
		
		Optional<Orders> optional = ordersRepository.findById(order.getOrderId());

		if(optional.isPresent()) {
			this.ordersRepository.save(order);
		}
		else {
			throw new OrdersException("Id not found");
		}
	}





}
