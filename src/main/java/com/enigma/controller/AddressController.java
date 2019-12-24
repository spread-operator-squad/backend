package com.enigma.controller;

import com.enigma.entities.Address;
import com.enigma.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public List<Address> getAllAddress(){
        return this.addressService.findAll();
    }

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable String id){
        return this.addressService.findAddressById(id);
    }

    @PostMapping
    public Address saveAddress(@RequestBody Address address){
        return this.addressService.saveAddress(address);
    }

    @PutMapping
    public Address updateAddress(@RequestBody Address address){
        return this.addressService.updateAddress(address);
    }

    @DeleteMapping("/{id}")
    public void deleteLocationByID(@PathVariable String id){
        this.addressService.deleteAddressById(id);
    }
}
