package com.enigma.repositories;

import com.enigma.entities.Role;
import com.enigma.enumeration.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByUserRoles(UserRoles role);
}
