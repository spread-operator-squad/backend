package com.enigma.controller;


import com.enigma.entities.Location;
import com.enigma.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    LocationService locationService;

    @GetMapping
    public List<Location> getAllLocation(){
        return this.locationService.findAll();
    }

    @GetMapping("{id}")
    public Location getLocationById(@PathVariable Integer id){
        return this.locationService.findLocationById(id);
    }

    @PostMapping
    public Location saveLocation(@RequestBody Location location){
        return this.locationService.saveLocation(location);
    }

    @PutMapping
    public Location updateLocation(@RequestBody Location location){
        return this.locationService.updateLocation(location);
    }
    @GetMapping("find/{type}")
    public Location findLocationByType(@PathVariable String type) {
        return this.locationService.findLocationByType(type);
    }

    @DeleteMapping("{id}")
    public void deleteLocationByID(@PathVariable Integer id){
        this.locationService.deleteLocationById(id);
    }
}
