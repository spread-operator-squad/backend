package com.enigma.controller;

import com.enigma.entities.Item;
import com.enigma.entities.Services;
import com.enigma.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    ServicesService servicesService;

    @PostMapping
    public Services saveService(@RequestBody Services services){
        return this.servicesService.saveServices(services);
    }

    @GetMapping("/{id}")
    public Services getServiceById(@PathVariable Integer id){
        return this.servicesService.findServicesById(id);
    }

    @GetMapping
    public List<Services> getAllService(){
        return this.servicesService.findAll();
    }

    @PutMapping
    public Services updateService(@RequestBody Services services){
        return this.servicesService.updateServices(services);
    }

    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable Integer id){
        this.servicesService.deleteServices(id);
    }

    @GetMapping("/store")
    public List<Services> getAllItemByStoreId(@RequestParam Integer id) {
        return this.servicesService.findAllServicesByIdStore(id);
    }
}
