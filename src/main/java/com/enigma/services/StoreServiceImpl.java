package com.enigma.services;


import com.enigma.constans.ResponseServiceConstants;
import com.enigma.constans.ResponseStoreConstants;
import com.enigma.entities.Services;
import com.enigma.entities.Store;
import com.enigma.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreRepository storeRepository;

    @Override
    public CustomResponse saveStore(Store store) {
        return new CustomResponse(new Status(HttpStatus.CREATED, ResponseStoreConstants.SUCCESS_SAVE_STORE), this.storeRepository.save(store));
    }

    @Override
    public CustomResponse findAllStore() {
        return new CustomResponse(new Status(HttpStatus.OK, ResponseStoreConstants.SUCCESS_GET_STORES), this.storeRepository.findAll());
    }

    @Override
    public CustomResponse deleteStore(Integer id) {
        this.storeRepository.delete((Store) this.findStoreById(id).getData());
        return new CustomResponse(new Status(HttpStatus.NO_CONTENT, ResponseStoreConstants.SUCCESS_DELETE_STORE));
    }

    @Override
    public CustomResponse findStoreById(Integer id) {
        if (!(this.storeRepository.findById(id).isPresent())) return new CustomResponse(new Status(HttpStatus.NOT_FOUND, "Store is not found!"));
        return new CustomResponse(new Status(HttpStatus.OK, ResponseStoreConstants.SUCCESS_GET_STORE), this.storeRepository.findById(id).get());
    }

    @Override
    public CustomResponse updateStore(Store store) {
        if (this.findStoreById(store.getId()).getStatus().getCode().equals(HttpStatus.NOT_FOUND.value())) return this.findStoreById(store.getId());
        else return new CustomResponse(new Status(HttpStatus.OK, ResponseStoreConstants.SUCCESS_UPDATE_STORE), this.saveStore(store).getData());
    }
}
