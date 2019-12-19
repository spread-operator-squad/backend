package com.enigma.controller;

import com.enigma.entities.CustomerExperience;
import com.enigma.services.CustomResponse;
import com.enigma.services.CustomerExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customerexperiences")
public class CustomerExperienceController {

    @Autowired
    CustomerExperienceService customerExperienceService;

    @PostMapping
    public ResponseEntity<CustomResponse> saveCustomerExperience(@RequestBody CustomerExperience customerExperience){
        CustomResponse response = this.customerExperienceService.saveCustomerExperience(customerExperience);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getCustomerExperienceById(@PathVariable Integer id){
        CustomResponse response = this.customerExperienceService.findCustomerExperienceById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getAllCustomerExperience(){
        CustomResponse response = this.customerExperienceService.findAll();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping
    public ResponseEntity<CustomResponse> updateCustomerExperience(@RequestBody CustomerExperience customerExperience){
        CustomResponse response = this.customerExperienceService.updateCustomerExperience(customerExperience);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteCustomerExperience(@PathVariable Integer id){
        CustomResponse response = this.customerExperienceService.deleteCustomerExperienceById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}
