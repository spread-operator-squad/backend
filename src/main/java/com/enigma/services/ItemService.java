package com.enigma.services;

import com.enigma.entities.Item;

public interface ItemService {
    CustomResponse saveItem(Item item);
    CustomResponse findAllItem();
    CustomResponse deleteItem(Integer id);
    CustomResponse findItemById(Integer id);
    CustomResponse updateItem(Item item);
}
