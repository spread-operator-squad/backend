package com.enigma.controller;

import com.enigma.entities.Item;
import com.enigma.entities.Services;
import com.enigma.services.CustomResponse;
import com.enigma.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    ServicesService servicesService;

    @PostMapping
    public ResponseEntity<CustomResponse> saveService(@RequestBody Services services){
        CustomResponse response = this.servicesService.saveService(services);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getServiceById(@PathVariable Integer id){
        CustomResponse response = this.servicesService.findServiceById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getAllService(){
        CustomResponse response = this.servicesService.findAllService();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping
    public ResponseEntity<CustomResponse> updateService(@RequestBody Services services){
        CustomResponse response = this.servicesService.updateService(services);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteService(@PathVariable Integer id){
        CustomResponse response = this.servicesService.deleteService(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

}
