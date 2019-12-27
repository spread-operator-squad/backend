package com.enigma.services.impl;


import com.enigma.constans.ResponseMessageTransaction;
import com.enigma.entities.*;
import com.enigma.exceptions.NotFoundException;
import com.enigma.repositories.TransactionRepository;
import com.enigma.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserService userService;

    @Autowired
    ServicesService servicesService;

    @Autowired
    ItemService itemService;

    @Autowired
    WalletService walletService;

    @Override
    public List<Transaction> findAll() {
        return this.transactionRepository.findAll();
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        transaction.setTotal(getTotalPrice(transaction));
        for (TransactionDetail detail : transaction.getTransactionDetails()) {
            detail.setTransaction(transaction);
        }
        return this.transactionRepository.save(transaction);
    }

    public BigDecimal getTotalPrice(Transaction transaction) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (TransactionDetail detail : transaction.getTransactionDetails()) {
            Services services = servicesService.findServicesById(detail.getServicesId());
            Item item = itemService.findItemById(detail.getItemId());
            detail.setSubtotal(services.getPrice().add(item.getPrice().multiply(new BigDecimal(detail.getWeight()))));
            totalPrice = totalPrice.add(detail.getSubtotal());
        }
        return totalPrice;
    }

    @Override
    public Transaction findTransactionById(Integer id) {
        if (!(this.transactionRepository.findById(id).isPresent()))
            throw new NotFoundException(ResponseMessageTransaction.FAILED_GET_TRANSACTION);
        return this.transactionRepository.findById(id).get();
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        this.findTransactionById(transaction.getId());
        switch (transaction.getType()) {
            case CASH:
                transaction.setChange(getChangeTransaction(transaction));
            case WALLET:
                transaction.setChange(getChangeTransaction(transaction));
                Wallet wallet = walletService.findWalletByUser(userService.findUserById(transaction.getCustomerId()));
                wallet.setBalance(wallet.getBalance().subtract(transaction.getTotal()));
        }
        return this.saveTransaction(transaction);
    }

    public BigDecimal getChangeTransaction(Transaction transaction) {
        return transaction.getTotal().subtract(transaction.getPay());
    }

    @Override
    public void deleteTransactionById(Integer id) {
        this.transactionRepository.delete(this.findTransactionById(id));
    }
}
