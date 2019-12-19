package com.enigma.services;

import com.enigma.constans.ResponseBackendServiceConstants;
import com.enigma.constans.ResponseItemConstants;
import com.enigma.entities.BackendService;
import com.enigma.entities.Item;
import com.enigma.repositories.BackendServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BackendServicesServiceImpl implements BackendServicesService {

    @Autowired
    BackendServiceRepository backendServiceRepository;

    @Override
    public CustomResponse findAll() {
        return new CustomResponse(new Status(HttpStatus.OK, ResponseBackendServiceConstants.SUCCESS_GET_BACKEND_SERVICES), this.backendServiceRepository.findAll());
    }

    @Override
    public CustomResponse saveBackendService(BackendService backendService) {
//        if (backendService.getUserId() != null) customerExperience.setUser((User) userService.findUserById(customerExperience.getUserId()).getData());
        return new CustomResponse(new Status(HttpStatus.CREATED, ResponseBackendServiceConstants.SUCCESS_SAVE_BACKEND_SERVICE), this.backendServiceRepository.save(backendService));
    }

    @Override
    public CustomResponse findBackendServiceById(Integer id) {
        if (!(this.backendServiceRepository.findById(id).isPresent())) return new CustomResponse(new Status(HttpStatus.NOT_FOUND, "Backend Service is not found"));
        return new CustomResponse(new Status(HttpStatus.OK, ResponseBackendServiceConstants.SUCCESS_GET_BACKEND_SERVICE), this.backendServiceRepository.findById(id).get());
    }

    @Override
    public CustomResponse updateBackendService(BackendService backendService) {
        if (this.findBackendServiceById(backendService.getId()).getStatus().getCode().equals(HttpStatus.NOT_FOUND.value())) return this.findBackendServiceById(backendService.getId());
        else return new CustomResponse(new Status(HttpStatus.OK, ResponseBackendServiceConstants.SUCCESS_UPDATE_BACKEND_SERVICE), this.saveBackendService(backendService).getData());
    }

    @Override
    public CustomResponse deleteBackendServiceById(Integer id) {
        this.backendServiceRepository.delete((BackendService) this.findBackendServiceById(id).getData());
        return new CustomResponse(new Status(HttpStatus.NO_CONTENT, ResponseBackendServiceConstants.SUCCESS_DELETE_BACKEND_SERVICE));
    }
}
