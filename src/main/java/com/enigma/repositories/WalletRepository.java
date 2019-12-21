package com.enigma.repositories;

import com.enigma.entities.User;
import com.enigma.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    Wallet findWalletByUser(User user);
}
