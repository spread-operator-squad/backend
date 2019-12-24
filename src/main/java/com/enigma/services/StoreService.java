package com.enigma.services;

import com.enigma.entities.Store;
import java.util.List;

public interface StoreService {
    List<Store> findAllStore();
    Store saveStore(Store store);
    Store findStoreById(Integer id);
    Store updateStore(Store store);
    void deleteStore(Integer id);
}
