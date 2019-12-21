package com.enigma.services;

import com.enigma.entities.Location;

import java.util.List;

public interface LocationService {
    List<Location> findAll();
    Location saveLocation(Location location);
    Location findLocationById(Integer id);
    Location findLocationByType(String type);
    Location updateLocation(Location location);
    void deleteLocationById(Integer id);
}
