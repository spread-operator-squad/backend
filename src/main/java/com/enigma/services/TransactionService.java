package com.enigma.services;

import com.enigma.entities.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findAll();
    Transaction saveTransaction(Transaction transaction);
    Transaction findTransactionById(Integer id);
    Transaction updateTransaction(Transaction transaction);
    void deleteTransactionById(Integer id);
}
