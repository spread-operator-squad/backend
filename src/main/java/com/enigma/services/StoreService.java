package com.enigma.services;

import com.enigma.entities.Store;
import com.enigma.services.impl.CustomResponse;

public interface StoreService {
    CustomResponse saveStore(Store store);
    CustomResponse findAllStore();
    CustomResponse deleteStore(Integer id);
    CustomResponse findStoreById(Integer id);
    CustomResponse updateStore(Store store);
}
