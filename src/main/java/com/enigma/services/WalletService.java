package com.enigma.services;

import com.enigma.entities.User;
import com.enigma.entities.Wallet;
import com.enigma.services.impl.CustomResponse;

public interface WalletService {
    CustomResponse findAllWallet();
    CustomResponse saveWallet(Wallet wallet);
    CustomResponse findWalletById(Integer id);
    CustomResponse updateWallet(Wallet wallet);
    CustomResponse deleteWalletById(Integer id);
    Wallet findWalletByUser(User user);
}
