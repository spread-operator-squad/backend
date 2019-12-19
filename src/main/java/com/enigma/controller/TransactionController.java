package com.enigma.controller;

import com.enigma.entities.Transaction;
import com.enigma.entities.User;
import com.enigma.services.CustomResponse;
import com.enigma.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping
    public ResponseEntity<CustomResponse> getAllUser(){
        CustomResponse response = this.transactionService.findAll();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getTransactionById(@PathVariable Integer id){
        CustomResponse response = this.transactionService.findTransactionById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PostMapping
    public ResponseEntity<CustomResponse> saveTransaction(@RequestBody Transaction transaction){
        CustomResponse response = this.transactionService.saveTransaction(transaction);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping
    public ResponseEntity<CustomResponse> updateTransaction(@RequestBody Transaction transaction){
        CustomResponse response = this.transactionService.updateTransaction(transaction);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteTransactionByID(@PathVariable Integer id){
        CustomResponse response = this.transactionService.deleteTransactionById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}
