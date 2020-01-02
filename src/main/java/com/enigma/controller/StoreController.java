package com.enigma.controller;

import com.enigma.entities.Store;
import com.enigma.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/stores")
public class StoreController {

    @Autowired
    StoreService storeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Store saveStore(@Valid  @RequestBody Store store){
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
    public Store updateStore(@Valid @RequestBody Store store){
        return this.storeService.updateStore(store);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStore(@PathVariable Integer id){
        this.storeService.deleteStore(id);
    }

    @GetMapping("/owner")
    public List<Store> getStoreByOwner(@RequestParam String id) {
        return this.storeService.findStoreByOwner(id);
    }
}
