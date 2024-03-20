package com.concentrix.project.service;

import com.concentrix.project.exception.ItemException;
import com.concentrix.project.model.Item;

public interface ItemService {

	void saveItem(Item item)throws ItemException;

	Item getItemById(int id)throws ItemException;

	void deleteItemById(int id) throws ItemException;


}
