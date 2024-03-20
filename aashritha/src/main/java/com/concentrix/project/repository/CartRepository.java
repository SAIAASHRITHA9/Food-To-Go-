package com.concentrix.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concentrix.project.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
