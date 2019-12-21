package com.enigma.services;

import com.enigma.entities.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAllItem();
    Item saveItem(Item item);
    Item findItemById(Integer id);
    Item updateItem(Item item);
    void deleteItem(Integer id);
}
