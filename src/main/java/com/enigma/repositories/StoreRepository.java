package com.enigma.repositories;

import com.enigma.entities.Store;
import com.enigma.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
    Store findByName(String name);
    List<Store> findAllByOwner(User owner);
}
