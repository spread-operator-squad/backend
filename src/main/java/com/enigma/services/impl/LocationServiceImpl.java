package com.enigma.services.impl;

import com.enigma.constans.ResponseMessageLocation;
import com.enigma.entities.Location;
import com.enigma.exceptions.NotFoundException;
import com.enigma.repositories.LocationRepository;
import com.enigma.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @Override
    public Location saveLocation(Location location) {
        if (location.getLocationId() != null) location.setLocation(findLocationById(location.getLocationId()));
        return this.locationRepository.save(location);
    }

    @Override
    public Location findLocationById(Integer id) {
        if (!(this.locationRepository.findById(id).isPresent())) throw new NotFoundException(ResponseMessageLocation.FAILED_GET_LOCATION);
        return this.locationRepository.findById(id).get();
    }

    @Override
    public Location findLocationByType(String type) {
        if (this.locationRepository.findByTypeContainingIgnoreCase(type) == null) throw new NotFoundException(ResponseMessageLocation.FAILED_GET_LOCATION);
        return this.locationRepository.findByTypeContainingIgnoreCase(type);
    }

    @Override
    public Location updateLocation(Location location) {
        this.findLocationById(location.getId());
        return this.saveLocation(location);
    }

    @Override
    public void deleteLocationById(Integer id) {
        this.locationRepository.delete(findLocationById(id));
    }
}
