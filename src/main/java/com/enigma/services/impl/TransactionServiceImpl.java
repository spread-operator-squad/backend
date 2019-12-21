package com.enigma.services.impl;


import com.enigma.constans.ResponseMessageTransaction;
import com.enigma.entities.*;
import com.enigma.repositories.TransactionDetailRepository;
import com.enigma.repositories.TransactionRepository;
import com.enigma.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionDetailRepository transactionDetailRepository;

    @Autowired
    UserService userService;

    @Autowired
    ServicesService servicesService;

    @Autowired
    ItemService itemService;

    @Autowired
    WalletService walletService;

    @Override
    public CustomResponse findAll(){
        return new CustomResponse(new Status(HttpStatus.OK, ResponseMessageTransaction.SUCCESS_GET_TRANSACTIONS), this.transactionRepository.findAll());
    }

    @Override
    public CustomResponse saveTransaction(Transaction transaction){
        transaction.setTotal(getTotalPrice(transaction));
        transactionHasDetails(transaction);
        return new CustomResponse(new Status(HttpStatus.CREATED, ResponseMessageTransaction.SUCCESS_SAVE_TRANSACTION), this.transactionRepository.save(transaction));
    }

    private void transactionHasDetails(Transaction transaction) {
        for (TransactionDetail detail:transaction.getTransactionDetails()){
            detail.setTransaction(transaction);
        }
    }

    private BigDecimal getTotalPrice(Transaction transaction) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (TransactionDetail detail : transaction.getTransactionDetails()){
            Services services = (Services) servicesService.findServiceById(detail.getServicesId()).getData();
            Item item = (Item) itemService.findItemById(detail.getItemId()).getData();
            detail.setSubtotal(services.getPrice().add(item.getPrice().multiply(new BigDecimal(detail.getWeight()))));
            totalPrice = totalPrice.add(detail.getSubtotal());
        }
        return totalPrice;
    }

    @Override
    public CustomResponse findTransactionById(Integer id){
        if (!(this.transactionRepository.findById(id).isPresent())) return new CustomResponse(new Status(HttpStatus.NOT_FOUND, "Transaction is not found"));
        return new CustomResponse(new Status(HttpStatus.OK, ResponseMessageTransaction.SUCCESS_GET_TRANSACTION), this.transactionRepository.findById(id).get());
    }

    @Override
    public CustomResponse updateTransaction(Transaction transaction){
        if (this.findTransactionById(transaction.getId()).getStatus().getCode().equals(HttpStatus.NOT_FOUND.value())) return this.findTransactionById(transaction.getId());
        switch (transaction.getType()){
            case "CASH":
                transaction.setChange(getChangeTransaction(transaction));
                break;
            case "WALLET":
                transaction.setChange(getChangeTransaction(transaction));
                Wallet wallet = walletService.findWalletByUser((User) userService.findUserById(transaction.getCustomerId()).getData());
                wallet.setSaldo(wallet.getSaldo().subtract(transaction.getTotal()));
                break;
        }
        return new CustomResponse(new Status(HttpStatus.OK, ResponseMessageTransaction.SUCCESS_UPDATE_TRANSACTION), this.saveTransaction(transaction).getData());
    }

    private BigDecimal getChangeTransaction(Transaction transaction) {
        return transaction.getTotal().subtract(transaction.getPay());
    }

    @Override
    public CustomResponse deleteTransactionById(Integer id) {
        this.transactionRepository.delete((Transaction) this.findTransactionById(id).getData());
        return new CustomResponse(new Status(HttpStatus.NO_CONTENT, ResponseMessageTransaction.SUCCESS_DELETE_TRANSACTION));
    }
}
