package com.enigma.services;

import com.enigma.entities.BackendService;

public interface BackendServicesService {
    CustomResponse findAll();
    CustomResponse saveBackendService(BackendService backendService);
    CustomResponse findBackendServiceById(Integer id);
    CustomResponse updateBackendService(BackendService backendService);
    CustomResponse deleteBackendServiceById(Integer id);
}
