package com.enigma.services;

import com.enigma.constans.ResponseWalletConstants;
import com.enigma.entities.Store;
import com.enigma.entities.User;
import com.enigma.entities.Wallet;
import com.enigma.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository walletRepository;

    @Autowired
    UserService userService;

    @Override
    public CustomResponse findAllWallet() {
        return new CustomResponse(new Status(HttpStatus.OK, ResponseWalletConstants.SUCCESS_GET_WALLETS), this.walletRepository.findAll());
    }

    @Override
    public CustomResponse saveWallet(Wallet wallet) {
        if (wallet.getUserId() != null) wallet.setUser((User) userService.findUserById(wallet.getUserId()).getData());
        return new CustomResponse(new Status(HttpStatus.CREATED, ResponseWalletConstants.SUCCESS_SAVE_WALLET), this.walletRepository.save(wallet));
    }

    @Override
    public CustomResponse findWalletById(Integer id) {
        if (!(this.walletRepository.findById(id).isPresent())) return new CustomResponse(new Status(HttpStatus.NOT_FOUND, "Wallet is not found"));
        return new CustomResponse(new Status(HttpStatus.OK, ResponseWalletConstants.SUCCESS_GET_WALLET), this.walletRepository.findById(id).get());
    }

    @Override
    public CustomResponse updateWallet(Wallet wallet) {
        if (this.findWalletById(wallet.getId()).getStatus().getCode().equals(HttpStatus.NOT_FOUND.value())) return this.findWalletById(wallet.getId());
        else return new CustomResponse(new Status(HttpStatus.OK, ResponseWalletConstants.SUCCESS_UPDATE_WALLET), this.saveWallet(wallet).getData());
    }

    @Override
    public CustomResponse deleteWalletById(Integer id) {
        this.walletRepository.delete((Wallet) this.findWalletById(id).getData());
        return new CustomResponse(new Status(HttpStatus.NO_CONTENT, ResponseWalletConstants.SUCCESS_DELETE_WALLET));
    }
}
