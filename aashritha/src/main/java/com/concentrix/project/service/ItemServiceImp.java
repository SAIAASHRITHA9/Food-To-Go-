package com.concentrix.project.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.concentrix.project.exception.ItemException;
import com.concentrix.project.model.Item;
import com.concentrix.project.repository.ItemRepository;

@Service
public class ItemServiceImp implements ItemService{
	
	@Autowired
	ItemRepository itemRepository;

	@Override
	public void saveItem(Item item) throws ItemException {
		// TODO Auto-generated method stub
		
		this.itemRepository.save(item);
		
	}

	@Override
	public Item getItemById(int id) throws ItemException {
		Optional<Item> optional = itemRepository.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new ItemException("The item is not found.");
		}
	}

	@Override
	public void deleteItemById(int id) throws ItemException {
		
		Optional<Item> optional = itemRepository.findById(id);
		if(optional.isPresent()) {
			this.itemRepository.deleteById(id);
		}
		else {
			throw new ItemException("The item is not found.");
		}
	}

}











































































































































































