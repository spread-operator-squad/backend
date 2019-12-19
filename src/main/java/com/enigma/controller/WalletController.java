package com.enigma.controller;

import com.enigma.entities.Wallet;
import com.enigma.services.impl.CustomResponse;
import com.enigma.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    @Autowired
    WalletService walletService;

    @PostMapping
    public ResponseEntity<CustomResponse> saveWallet(@RequestBody Wallet wallet){
        CustomResponse response = this.walletService.saveWallet(wallet);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse> getWalletById(@PathVariable Integer id){
        CustomResponse response = this.walletService.findWalletById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @GetMapping
    public ResponseEntity<CustomResponse> getAllWallet(){
        CustomResponse response = this.walletService.findAllWallet();
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @PutMapping
    public ResponseEntity<CustomResponse> updateWallet(@RequestBody Wallet wallet){
        CustomResponse response = this.walletService.updateWallet(wallet);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse> deleteWallet(@PathVariable Integer id){
        CustomResponse response = this.walletService.deleteWalletById(id);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getCode()));
    }
}
