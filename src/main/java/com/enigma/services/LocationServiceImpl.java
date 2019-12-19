package com.enigma.services;

import com.enigma.constans.ResponseMessageLocation;
import com.enigma.entities.Location;
import com.enigma.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Override
    public CustomResponse findAll(){
        return new CustomResponse(new Status(HttpStatus.OK, ResponseMessageLocation.SUCCESS_GET_LOCATIONS), this.locationRepository.findAll());
    }

    @Override
    public CustomResponse saveLocation(Location location){
        if (location.getLocationId() != null) location.setLocation((Location) findLocationById(location.getLocationId()).getData());
        return new CustomResponse(new Status(HttpStatus.CREATED,ResponseMessageLocation.SUCCESS_SAVE_LOCATION), this.locationRepository.save(location));
    }

    @Override
    public CustomResponse findLocationById(Integer id){
        if (!(this.locationRepository.findById(id).isPresent())) return new CustomResponse(new Status(HttpStatus.NOT_FOUND, "Location is not found"));
        return new CustomResponse(new Status(HttpStatus.OK, ResponseMessageLocation.SUCCESS_GET_LOCATION), this.locationRepository.findById(id).get());
    }

    @Override
    public CustomResponse findLocationByType(String type) {
        if (this.locationRepository.findByTypeContainingIgnoreCase(type) == null) return new CustomResponse(new Status(HttpStatus.NOT_FOUND, "Type is not found"));
        return new CustomResponse(new Status(HttpStatus.OK, ResponseMessageLocation.SUCCESS_GET_LOCATION), this.locationRepository.findByTypeContainingIgnoreCase(type));
    }

    @Override
    public CustomResponse updateLocation(Location location){
        if (this.findLocationById(location.getId()).getStatus().getCode().equals(HttpStatus.NOT_FOUND.value())) return this.findLocationById(location.getId());
        else return new CustomResponse(new Status(HttpStatus.OK,ResponseMessageLocation.SUCCESS_UPDATE_LOCATION), this.saveLocation(location).getData());
    }

    @Override
    public CustomResponse deleteLocationById(Integer id) {
        this.locationRepository.delete((Location) this.findLocationById(id).getData());
        return new CustomResponse(new Status(HttpStatus.NO_CONTENT,ResponseMessageLocation.SUCCESS_DELETE_LOCATION));
    }
}
