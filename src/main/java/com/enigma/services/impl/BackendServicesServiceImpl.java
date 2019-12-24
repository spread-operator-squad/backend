package com.enigma.services.impl;

import com.enigma.constans.ResponseMessageBackendService;
import com.enigma.entities.BackendService;
import com.enigma.exceptions.NotFoundException;
import com.enigma.repositories.BackendServiceRepository;
import com.enigma.services.BackendServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackendServicesServiceImpl implements BackendServicesService {

    @Autowired
    BackendServiceRepository backendServiceRepository;

    @Override
    public List<BackendService> findAll() {
        return this.backendServiceRepository.findAll();
    }

    @Override
    public BackendService saveBackendService(BackendService backendService) {
        return this.backendServiceRepository.save(backendService);
    }

    @Override
    public BackendService findBackendServiceById(Integer id) {
        if (!(this.backendServiceRepository.findById(id).isPresent())) throw new NotFoundException(ResponseMessageBackendService.FAILED_GET_BACKEND_SERVICE);
        return this.backendServiceRepository.findById(id).get();
    }

    @Override
    public BackendService updateBackendService(BackendService backendService) {
        findBackendServiceById(backendService.getId());
        return this.saveBackendService(backendService);
    }

    @Override
    public void deleteBackendServiceById(Integer id) {
        this.backendServiceRepository.delete(findBackendServiceById(id));
    }
}
