package com.enigma.services;

import com.enigma.entities.Transaction;
import com.enigma.services.impl.CustomResponse;

public interface TransactionService {
    CustomResponse findAll();
    CustomResponse saveTransaction(Transaction transaction);
    CustomResponse findTransactionById(Integer id);
    CustomResponse updateTransaction(Transaction transaction);
    CustomResponse deleteTransactionById(Integer id);
}
