package com.concentrix.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concentrix.project.exception.CustomerException;
import com.concentrix.project.model.Customer;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Integer>{
	
	Customer getCustomerByCustomerEmail(String customerEmail)throws CustomerException;

}
