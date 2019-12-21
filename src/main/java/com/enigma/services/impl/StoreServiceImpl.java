package com.enigma.services.impl;


import com.enigma.constans.ResponseMessageStore;
import com.enigma.entities.Store;
import com.enigma.exceptions.NotFoundException;
import com.enigma.repositories.StoreRepository;
import com.enigma.services.StoreService;
import com.enigma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    UserService userService;

    @Override
    public List<Store> findAllStore() {
        return this.storeRepository.findAll();
    }

    @Override
    public Store saveStore(Store store) {
        if (!(store.getOwnerId().isEmpty())) store.setOwner(userService.findUserById(store.getOwnerId()));
        return storeRepository.save(store);
    }

    @Override
    public Store findStoreById(Integer id) {
        if (!(this.storeRepository.findById(id).isPresent())) throw new NotFoundException(ResponseMessageStore.FAILED_GET_STORE);
        return this.storeRepository.findById(id).get();
    }

    @Override
    public Store updateStore(Store store) {
        this.findStoreById(store.getId());
        return this.saveStore(store);
    }

    @Override
    public void deleteStore(Integer id) {
        this.storeRepository.delete(this.findStoreById(id));
    }
}
