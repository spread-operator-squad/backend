package com.enigma.services.impl;

import com.enigma.constans.ResponseMessageWallet;
import com.enigma.entities.User;
import com.enigma.entities.Wallet;
import com.enigma.exceptions.NotFoundException;
import com.enigma.repositories.WalletRepository;
import com.enigma.services.UserService;
import com.enigma.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    UserService userService;

    @Override
    public List<Wallet> findAllWallet() {
        return this.walletRepository.findAll();
    }

    @Override
    public Wallet saveWallet(Wallet wallet) {
        if (wallet.getUserId() != null) wallet.setUser(this.userService.findUserById(wallet.getUserId()));
        return this.walletRepository.save(wallet);
    }

    @Override
    public Wallet findWalletById(Integer id) {
        if (!(this.walletRepository.findById(id).isPresent())) throw new NotFoundException(ResponseMessageWallet.FAILED_GET_WALLET);
        return this.walletRepository.findById(id).get();
    }

    @Override
    public Wallet updateWallet(Wallet wallet) {
        this.findWalletById(wallet.getId());
        return this.saveWallet(wallet);
    }

    @Override
    public void deleteWalletById(Integer id) {
        this.walletRepository.delete(this.findWalletById(id));
    }

    @Override
    public Wallet findWalletByUser(User user) {
        return this.walletRepository.findWalletByUser(user);
    }
}
