package com.enigma.controller;

import com.enigma.entities.BackendService;
import com.enigma.services.BackendServicesService;
import com.enigma.services.impl.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/backendservices")
public class BackendServiceController {

    @Autowired
    BackendServicesService backendServicesService;

    @PostMapping
    public ResponseEntity<CustomResponse> saveBackendService(@RequestBody BackendService backendService){
        CustomResponse response = this.backendServicesService.saveBackendService(backendService);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getBackendServiceById(@PathVariable Integer id){
        CustomResponse response = this.backendServicesService.findBackendServiceById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getAllBackendService(){
        CustomResponse response = this.backendServicesService.findAll();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping
    public ResponseEntity<CustomResponse> updateBackendService(@RequestBody BackendService backendService){
        CustomResponse response = this.backendServicesService.updateBackendService(backendService);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteBackendService(@PathVariable Integer id){
        CustomResponse response = this.backendServicesService.deleteBackendServiceById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}
