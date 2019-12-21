package com.enigma.controller;

import com.enigma.entities.Store;
import com.enigma.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    StoreService storeService;

    @PostMapping
    public Store saveStore(@RequestBody Store store){
        return this.storeService.saveStore(store);
    }

    @GetMapping("/{id}")
    public Store getStoreById(@PathVariable Integer id){
        return this.storeService.findStoreById(id);
    }

    @GetMapping
    public List<Store> getAllStore(){
        return this.storeService.findAllStore();
    }

    @PutMapping
    public Store updateStore(@RequestBody Store store){
        return this.storeService.updateStore(store);
    }

    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable Integer id){
        this.storeService.deleteStore(id);
    }

}
