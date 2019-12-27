package com.enigma.services;

import com.enigma.entities.Services;

import java.util.List;

public interface ServicesService {
    List<Services> findAll();
    Services saveServices(Services services);
    Services findServicesById(Integer id);
    Services updateServices(Services services);
    void deleteServices(Integer id);
    List<Services> findAllServicesByIdStore(Integer id);
}
