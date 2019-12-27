package com.enigma.repositories;

import com.enigma.entities.Services;
import com.enigma.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Services, Integer> {
    Services findByName(String name);
    List<Services> findAllByStore(Store store);
}
