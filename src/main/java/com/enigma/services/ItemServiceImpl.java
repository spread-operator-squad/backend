package com.enigma.services;

import com.enigma.constans.ResponseItemConstants;
import com.enigma.entities.Item;
import com.enigma.entities.Store;
import com.enigma.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    StoreService storeService;

    @Override
    public CustomResponse saveItem(Item item){
        if (item.getStoreId() != null) item.setStores((Store) storeService.findStoreById(item.getStoreId()).getData());
        return new CustomResponse(new Status(HttpStatus.CREATED, ResponseItemConstants.SUCCESS_SAVE_ITEM), this.itemRepository.save(item));
    }

    @Override
    public CustomResponse findAllItem() {
        return new CustomResponse(new Status(HttpStatus.OK, ResponseItemConstants.SUCCESS_GET_ITEMS), this.itemRepository.findAll());
    }

    @Override
    public CustomResponse deleteItem(Integer id) {
        this.itemRepository.delete((Item) this.findItemById(id).getData());
        return new CustomResponse(new Status(HttpStatus.NO_CONTENT,ResponseItemConstants.SUCCESS_DELETE_ITEM));
    }

    @Override
    public CustomResponse findItemById(Integer id) {
        if (!(this.itemRepository.findById(id).isPresent())) return new CustomResponse(new Status(HttpStatus.NOT_FOUND, "Item is not found!"));
        return new CustomResponse(new Status(HttpStatus.OK, ResponseItemConstants.SUCCESS_GET_ITEM), this.itemRepository.findById(id).get());
    }

    @Override
    public CustomResponse updateItem(Item item) {
        if (this.findItemById(item.getId()).getStatus().getCode().equals(HttpStatus.NOT_FOUND.value())) return this.findItemById(item.getId());
        else return new CustomResponse(new Status(HttpStatus.OK,ResponseItemConstants.SUCCESS_UPDATE_ITEM), this.saveItem(item).getData());
    }
}
