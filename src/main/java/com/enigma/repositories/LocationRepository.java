package com.enigma.repositories;

import com.enigma.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    Location findByTypeContainingIgnoreCase(String type);
}
