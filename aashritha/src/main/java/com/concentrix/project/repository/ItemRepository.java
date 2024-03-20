package com.concentrix.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.concentrix.project.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{


}
