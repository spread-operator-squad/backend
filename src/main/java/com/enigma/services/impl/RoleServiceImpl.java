package com.enigma.services.impl;

import com.enigma.entities.Role;
import com.enigma.enumeration.UserRoles;
import com.enigma.repositories.RoleRepository;
import com.enigma.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements CommandLineRunner, RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findAll().isEmpty()) {
            this.roleRepository.deleteAll();
            // Create Role
            List<Role> roles = new ArrayList<>();
            for (UserRoles userRole : UserRoles.values()) {
                roles.add(new Role(userRole));
            }
            this.roleRepository.saveAll(roles);
        }
    }

    @Override
    public Role getRoleByRoles(UserRoles role) {
        return roleRepository.findRoleByUserRoles(role);
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleRepository.findById(id).get();
    }
}
