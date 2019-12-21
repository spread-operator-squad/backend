package com.enigma.controller;

import com.enigma.entities.CustomerExperience;
import com.enigma.services.CustomerExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer-experiences")
public class CustomerExperienceController {

    @Autowired
    CustomerExperienceService customerExperienceService;

    @GetMapping
    public List<CustomerExperience> getAllCustomerExperience(){
        return this.customerExperienceService.findAll();
    }

    @PostMapping
    public CustomerExperience saveCustomerExperience(@RequestBody CustomerExperience customerExperience){
        return this.customerExperienceService.saveCustomerExperience(customerExperience);
    }

    @GetMapping("/{id}")
    public CustomerExperience getCustomerExperienceById(@PathVariable Integer id){
        return this.customerExperienceService.findCustomerExperienceById(id);
    }

    @PutMapping
    public CustomerExperience updateCustomerExperience(@RequestBody CustomerExperience customerExperience){
        return this.customerExperienceService.updateCustomerExperience(customerExperience);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerExperience(@PathVariable Integer id){
        this.customerExperienceService.deleteCustomerExperienceById(id);
    }
}
