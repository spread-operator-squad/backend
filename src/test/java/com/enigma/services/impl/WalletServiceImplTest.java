package com.enigma.services.impl;

import com.enigma.entities.User;
import com.enigma.entities.Wallet;
import com.enigma.repositories.WalletRepository;
import com.enigma.services.UserService;
import com.enigma.services.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class WalletServiceImplTest {

    @MockBean
    WalletRepository walletRepository;

    @MockBean
    UserService userService;

    @SpyBean
    WalletService walletService;

    @Test
    void findAllWallet_should_call_walletRepository_findAll_once() {
        walletService.findAllWallet();
        Mockito.verify(walletRepository, Mockito.times(1)).findAll();
    }

    @Test
    void saveWallet_should_call_walletRepository_save_once() {
        Wallet wallet = new Wallet();
        wallet.setId(1);
        wallet.setUserId("id");
        walletService.saveWallet(wallet);
        Mockito.verify(walletRepository, Mockito.times(1)).save(wallet);
    }

    @Test
    void findWalletById_should_call_walletRepository_findById_twice() {
        Wallet wallet = new Wallet();
        wallet.setId(1);
        wallet.setUserId("id");
        Mockito.when(walletRepository.findById(1)).thenReturn(Optional.of(wallet));
        walletService.findWalletById(1);
        Mockito.verify(walletRepository, Mockito.times(2)).findById(1);
    }

    @Test
    void updateWallet_should_call_walletService_save_once() {
        Wallet wallet = new Wallet();
        wallet.setId(1);
        wallet.setUserId("id");
        Mockito.when(walletRepository.findById(1)).thenReturn(Optional.of(wallet));
        walletService.updateWallet(wallet);
        Mockito.verify(walletService, Mockito.times(1)).saveWallet(wallet);
    }

    @Test
    void deleteWalletById_should_call_walletRepository_delete_once() {
        Wallet wallet = new Wallet();
        wallet.setId(1);
        wallet.setUserId("id");
        Mockito.when(walletRepository.findById(1)).thenReturn(Optional.of(wallet));
        walletService.deleteWalletById(1);
        Mockito.verify(walletRepository, Mockito.times(1)).delete(wallet);
    }

    @Test
    void findWalletByUser_should_call_walletRepository_findWalletByUser_once() {
        Wallet wallet = new Wallet();
        wallet.setId(1);
        wallet.setUserId("id");
        Mockito.when(walletRepository.findWalletByUser(new User())).thenReturn(wallet);
        walletService.findWalletByUser(new User());
        Mockito.verify(walletRepository, Mockito.times(1)).findWalletByUser(new User());
    }
}
