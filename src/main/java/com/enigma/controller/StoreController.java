package com.enigma.controller;

import com.enigma.entities.Services;
import com.enigma.entities.Store;
import com.enigma.services.CustomResponse;
import com.enigma.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    StoreService storeService;

    @PostMapping
    public ResponseEntity<CustomResponse> saveStore(@RequestBody Store store){
        CustomResponse response = this.storeService.saveStore(store);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getStoreById(@PathVariable Integer id){
        CustomResponse response = this.storeService.findStoreById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getAllStore(){
        CustomResponse response = this.storeService.findAllStore();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping
    public ResponseEntity<CustomResponse> updateStore(@RequestBody Store store){
        CustomResponse response = this.storeService.updateStore(store);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteStore(@PathVariable Integer id){
        CustomResponse response = this.storeService.deleteStore(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

}
