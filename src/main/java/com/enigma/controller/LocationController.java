package com.enigma.controller;


import com.enigma.entities.Location;
import com.enigma.services.impl.CustomResponse;
import com.enigma.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping
    public ResponseEntity<CustomResponse> getAllLocation(){
        CustomResponse response = this.locationService.findAll();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomResponse> getLocationById(@PathVariable Integer id){
        CustomResponse response = this.locationService.findLocationById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PostMapping
    public ResponseEntity<CustomResponse> saveLocation(@RequestBody Location location){
        CustomResponse response = this.locationService.saveLocation(location);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping
    public ResponseEntity<CustomResponse> updateLocation(@RequestBody Location location){
        CustomResponse response = this.locationService.updateLocation(location);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
    @GetMapping("find/{type}")
    public ResponseEntity<CustomResponse> findLocationByType(@PathVariable String type) {
        CustomResponse response = this.locationService.findLocationByType(type);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CustomResponse> deleteLocationByID(@PathVariable Integer id){
        CustomResponse response = this.locationService.deleteLocationById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}
