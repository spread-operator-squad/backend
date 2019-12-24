package com.enigma.services;

import com.enigma.entities.BackendService;

import java.util.List;

public interface BackendServicesService {
    List<BackendService> findAll();
    BackendService saveBackendService(BackendService backendService);
    BackendService findBackendServiceById(Integer id);
    BackendService updateBackendService(BackendService backendService);
    void deleteBackendServiceById(Integer id);
}
