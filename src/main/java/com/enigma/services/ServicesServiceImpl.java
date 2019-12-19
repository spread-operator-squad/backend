package com.enigma.services;

import com.enigma.constans.ResponseServiceConstants;
import com.enigma.entities.Services;
import com.enigma.entities.Store;
import com.enigma.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ServicesServiceImpl implements ServicesService {

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    StoreService storeService;

    @Override
    public CustomResponse saveService(Services services) {
        if (services.getStoreId() != null) services.setStores((Store) storeService.findStoreById(services.getStoreId()).getData());
        return new CustomResponse(new Status(HttpStatus.CREATED, ResponseServiceConstants.SUCCESS_SAVE_SERVICE), this.serviceRepository.save(services));
    }

    @Override
    public CustomResponse findAllService() {
        return new CustomResponse(new Status(HttpStatus.OK, ResponseServiceConstants.SUCCESS_GET_SERVICES), this.serviceRepository.findAll());
    }

    @Override
    public CustomResponse deleteService(Integer id) {
        this.serviceRepository.delete((Services) this.findServiceById(id).getData());
        return new CustomResponse(new Status(HttpStatus.NO_CONTENT, ResponseServiceConstants.SUCCESS_DELETE_SERVICE));
    }

    @Override
    public CustomResponse findServiceById(Integer id) {
        if (!(this.serviceRepository.findById(id).isPresent())) return new CustomResponse(new Status(HttpStatus.NOT_FOUND, "Service is not found!"));
        return new CustomResponse(new Status(HttpStatus.OK, ResponseServiceConstants.SUCCESS_GET_SERVICE), this.serviceRepository.findById(id).get());
    }

    @Override
    public CustomResponse updateService(Services services) {
        if (this.findServiceById(services.getId()).getStatus().getCode().equals(HttpStatus.NOT_FOUND.value())) return this.findServiceById(services.getId());
        else return new CustomResponse(new Status(HttpStatus.OK, ResponseServiceConstants.SUCCESS_UPDATE_SERVICE), this.saveService(services).getData());
    }
}
