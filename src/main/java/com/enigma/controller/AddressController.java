package com.enigma.controller;


import com.enigma.entities.Address;
import com.enigma.services.AddressService;
import com.enigma.services.impl.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public ResponseEntity<CustomResponse> getAllAddress(){
        CustomResponse response = this.addressService.findAll();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getAddressById(@PathVariable String id){
        CustomResponse response = this.addressService.findAddressById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PostMapping
    public ResponseEntity<CustomResponse> saveAddress(@RequestBody Address address){
        CustomResponse response = this.addressService.saveAddress(address);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping
    public ResponseEntity<CustomResponse> updateAddress(@RequestBody Address address){
        CustomResponse response = this.addressService.updateAddress(address);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteLocationByID(@PathVariable String id){
        CustomResponse response = this.addressService.deleteAddressById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}
