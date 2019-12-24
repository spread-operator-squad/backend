package com.enigma.controller;

import com.enigma.entities.BackendService;
import com.enigma.services.BackendServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backend-services")
public class BackendServiceController {

    @Autowired
    BackendServicesService backendServicesService;

    @GetMapping
    public List<BackendService> getAllBackendService(){
        return this.backendServicesService.findAll();
    }

    @PostMapping
    public BackendService saveBackendService(@RequestBody BackendService backendService){
        return this.backendServicesService.saveBackendService(backendService);
    }

    @GetMapping("/{id}")
    public BackendService getBackendServiceById(@PathVariable Integer id){
        return this.backendServicesService.findBackendServiceById(id);
    }

    @PutMapping
    public BackendService updateBackendService(@RequestBody BackendService backendService){
        return this.backendServicesService.updateBackendService(backendService);
    }

    @DeleteMapping("/{id}")
    public void deleteBackendService(@PathVariable Integer id){
        this.backendServicesService.deleteBackendServiceById(id);
    }
}
