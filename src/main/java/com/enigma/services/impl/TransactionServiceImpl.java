package com.enigma.services.impl;


import com.enigma.constans.ResponseMessageTransaction;
import com.enigma.entities.Transaction;
import com.enigma.repositories.TransactionRepository;
import com.enigma.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public CustomResponse findAll(){
        return new CustomResponse(new Status(HttpStatus.OK, ResponseMessageTransaction.SUCCESS_GET_TRANSACTIONS), this.transactionRepository.findAll());
    }

    @Override
    public CustomResponse saveTransaction(Transaction transaction){
        return new CustomResponse(new Status(HttpStatus.CREATED, ResponseMessageTransaction.SUCCESS_SAVE_TRANSACTION), this.transactionRepository.save(transaction));
    }

    @Override
    public CustomResponse findTransactionById(Integer id){
        if (!(this.transactionRepository.findById(id).isPresent())) return new CustomResponse(new Status(HttpStatus.NOT_FOUND, "Transaction is not found"));
        return new CustomResponse(new Status(HttpStatus.OK, ResponseMessageTransaction.SUCCESS_GET_TRANSACTION), this.transactionRepository.findById(id).get());
    }

    @Override
    public CustomResponse updateTransaction(Transaction transaction){
        if (this.findTransactionById(transaction.getId()).getStatus().getCode().equals(HttpStatus.NOT_FOUND.value())) return this.findTransactionById(transaction.getId());
        else return new CustomResponse(new Status(HttpStatus.OK, ResponseMessageTransaction.SUCCESS_UPDATE_TRANSACTION), this.saveTransaction(transaction).getData());
    }

    @Override
    public CustomResponse deleteTransactionById(Integer id) {
        this.transactionRepository.delete((Transaction) this.findTransactionById(id).getData());
        return new CustomResponse(new Status(HttpStatus.NO_CONTENT, ResponseMessageTransaction.SUCCESS_DELETE_TRANSACTION));
    }
}
