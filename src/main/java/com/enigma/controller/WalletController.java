package com.enigma.controller;

import com.enigma.entities.Wallet;
import com.enigma.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    @Autowired
    WalletService walletService;

    @PostMapping
    public Wallet saveWallet(@RequestBody Wallet wallet){
        return this.walletService.saveWallet(wallet);
    }

    @GetMapping("/{id}")
    public Wallet getWalletById(@PathVariable Integer id){
        return this.walletService.findWalletById(id);
    }

    @GetMapping
    public List<Wallet> getAllWallet(){
        return this.walletService.findAllWallet();
    }

    @PutMapping
    public Wallet updateWallet(@RequestBody Wallet wallet){
        return this.walletService.updateWallet(wallet);
    }

    @DeleteMapping("/{id}")
    public void deleteWallet(@PathVariable Integer id){
        this.walletService.deleteWalletById(id);
    }
}
