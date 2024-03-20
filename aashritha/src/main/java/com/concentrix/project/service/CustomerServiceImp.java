package com.concentrix.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concentrix.project.exception.CustomerException;
import com.concentrix.project.model.Customer;
import com.concentrix.project.repository.CustomerRepository;

@Service
public class CustomerServiceImp implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public Customer getCustomerByEmail(String customerEmail) throws CustomerException {
		
		Customer customer = customerRepository.getCustomerByCustomerEmail(customerEmail);
		
		if(customer != null) {
			
			return customer;
		}
		else {
			
			throw new CustomerException();
		}
		
		
	}

	@Override
	public void saveCustomer(Customer customer) throws CustomerException {
		
		this.customerRepository.save(customer);
		
	}

	@Override
	public Customer getCustomerById(int customerId) throws CustomerException {
		// TODO Auto-generated method stub
		Optional<Customer> optional = customerRepository.findById(customerId);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new CustomerException("The customer id not found");
		}
	}

}
