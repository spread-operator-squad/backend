package com.enigma.controller;

import com.enigma.entities.Transaction;
import com.enigma.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping
    public List<Transaction> getAllTransaction(){
        return this.transactionService.findAll();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Integer id){
        return this.transactionService.findTransactionById(id);
    }

    @PostMapping
    public Transaction saveTransaction(@RequestBody Transaction transaction){
        return this.transactionService.saveTransaction(transaction);
    }

    @PutMapping
    public Transaction updateTransaction(@RequestBody Transaction transaction){
        return this.transactionService.updateTransaction(transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransactionByID(@PathVariable Integer id){
        this.transactionService.deleteTransactionById(id);
    }
}
