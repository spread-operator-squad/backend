package com.enigma.services.impl;

import com.enigma.constans.ResponseMessageService;
import com.enigma.entities.Services;
import com.enigma.exceptions.NotFoundException;
import com.enigma.repositories.ServiceRepository;
import com.enigma.services.ServicesService;
import com.enigma.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesServiceImpl implements ServicesService {

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    StoreService storeService;

    @Override
    public List<Services> findAll() {
        return this.serviceRepository.findAll();
    }

    @Override
    public Services saveServices(Services services) {
        if (services.getStoreId() != null) services.setStores(storeService.findStoreById(services.getStoreId()));
        return this.serviceRepository.save(services);
    }

    @Override
    public Services findServicesById(Integer id) {
        if (!(this.serviceRepository.findById(id).isPresent())) throw new NotFoundException(ResponseMessageService.FAILED_GET_SERVICE);
        return this.serviceRepository.findById(id).get();
    }

    @Override
    public Services updateServices(Services services) {
        this.findServicesById(services.getId());
        return this.saveServices(services);
    }

    @Override
    public void deleteServices(Integer id) {
        this.serviceRepository.delete(this.findServicesById(id));
    }
}
