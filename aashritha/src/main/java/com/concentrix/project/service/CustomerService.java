package com.concentrix.project.service;

import com.concentrix.project.exception.CustomerException;
import com.concentrix.project.model.Customer;

public interface CustomerService {

	Customer getCustomerByEmail(String customerEmail) throws CustomerException;

	void saveCustomer(Customer customer) throws CustomerException;

	Customer getCustomerById(int customerId)throws CustomerException;

}
