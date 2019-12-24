package com.enigma.services;

import com.enigma.entities.User;
import com.enigma.entities.Wallet;

import java.util.List;

public interface WalletService {
    List<Wallet> findAllWallet();
    Wallet saveWallet(Wallet wallet);
    Wallet findWalletById(Integer id);
    Wallet updateWallet(Wallet wallet);
    void deleteWalletById(Integer id);
    Wallet findWalletByUser(User user);
}
