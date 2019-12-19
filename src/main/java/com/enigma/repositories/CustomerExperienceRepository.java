package com.enigma.repositories;

import com.enigma.entities.CustomerExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerExperienceRepository extends JpaRepository<CustomerExperience, Integer> {
}
