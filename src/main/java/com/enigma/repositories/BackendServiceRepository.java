package com.enigma.repositories;

import com.enigma.entities.BackendService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackendServiceRepository extends JpaRepository<BackendService, Integer> {
}
