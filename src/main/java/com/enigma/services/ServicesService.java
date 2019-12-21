package com.enigma.services;

import com.enigma.entities.Services;
import com.enigma.services.impl.CustomResponse;

public interface ServicesService {
    CustomResponse saveService(Services services);
    CustomResponse findAllService();
    CustomResponse deleteService(Integer id);
    CustomResponse findServiceById(Integer id);
    CustomResponse updateService(Services services);
}
