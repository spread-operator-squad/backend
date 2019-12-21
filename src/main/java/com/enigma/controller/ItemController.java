package com.enigma.controller;

import com.enigma.entities.Item;
import com.enigma.services.impl.CustomResponse;
import com.enigma.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping
    public ResponseEntity<CustomResponse> saveItem(@RequestBody Item item){
        CustomResponse response = this.itemService.saveItem(item);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getItemById(@PathVariable Integer id){
        CustomResponse response = this.itemService.findItemById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getAllItem(){
        CustomResponse response = this.itemService.findAllItem();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping
    public ResponseEntity<CustomResponse> updateItem(@RequestBody Item item){
        CustomResponse response = this.itemService.updateItem(item);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteItem(@PathVariable Integer id){
        CustomResponse response = this.itemService.deleteItem(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

}
