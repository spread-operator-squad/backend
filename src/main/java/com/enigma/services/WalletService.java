package com.enigma.services;

import com.enigma.entities.Wallet;

public interface WalletService {
    CustomResponse findAllWallet();
    CustomResponse saveWallet(Wallet wallet);
    CustomResponse findWalletById(Integer id);
    CustomResponse updateWallet(Wallet wallet);
    CustomResponse deleteWalletById(Integer id);
}
