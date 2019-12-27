package com.enigma.services.impl;

import com.enigma.constans.ResponseMessageItem;
import com.enigma.entities.Item;
import com.enigma.exceptions.NotFoundException;
import com.enigma.repositories.ItemRepository;
import com.enigma.services.ItemService;
import com.enigma.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    StoreService storeService;

    @Override
    public List<Item> findAllItem() {
        return this.itemRepository.findAll();
    }

    @Override
    public Item saveItem(Item item) {
        if (item.getStoreId() != null) item.setStore(storeService.findStoreById(item.getStoreId()));
        return this.itemRepository.save(item);
    }

    @Override
    public Item findItemById(Integer id) {
        if (!(this.itemRepository.findById(id).isPresent())) throw new NotFoundException(ResponseMessageItem.FAILED_GET_ITEM);
        return this.itemRepository.findById(id).get();
    }

    @Override
    public Item updateItem(Item item) {
        this.findItemById(item.getId());
        return this.saveItem(item);
    }

    @Override
    public void deleteItem(Integer id) {
        this.itemRepository.delete(findItemById(id));

    }

    @Override
    public List<Item> findAllItemByIdStore(Integer id) {
        return this.itemRepository.findAllByStore(this.storeService.findStoreById(id));
    }
}
