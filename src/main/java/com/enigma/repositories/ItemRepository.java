package com.enigma.repositories;

import com.enigma.entities.Item;
import com.enigma.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findByName(String name);
    List<Item> findAllByStore(Store store);
}
