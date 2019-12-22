package com.enigma.controller;

import com.enigma.entities.Item;
import com.enigma.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @PostMapping
    public Item saveItem(@RequestBody Item item){
        return this.itemService.saveItem(item);
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Integer id){
        return this.itemService.findItemById(id);
    }

    @GetMapping
    public List<Item> getAllItem(){
        return this.itemService.findAllItem();
    }

    @PutMapping
    public Item updateItem(@RequestBody Item item){
        return this.itemService.updateItem(item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Integer id){
        this.itemService.deleteItem(id);
    }

}
