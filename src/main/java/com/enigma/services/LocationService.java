package com.enigma.services;

import com.enigma.entities.Location;
import com.enigma.services.impl.CustomResponse;

public interface LocationService {
    CustomResponse findAll();
    CustomResponse saveLocation(Location location);
    CustomResponse findLocationById(Integer id);
    CustomResponse findLocationByType(String type);
    CustomResponse updateLocation(Location location);
    CustomResponse deleteLocationById(Integer id);
}
