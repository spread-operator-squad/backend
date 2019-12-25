package com.enigma.services.impl;

import com.enigma.entities.Role;
import com.enigma.enumeration.UserRoles;
import com.enigma.repositories.RoleRepository;
import com.enigma.services.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RoleServiceImplTest {

    @MockBean
    RoleRepository roleRepository;

    @SpyBean
    RoleService roleService;

    @Test
    void getRoleByRoles_should_call_roleRepository_findRoleByUserRole_once() {
        roleService.getRoleByRoles(UserRoles.OWNER);
        Mockito.verify(roleRepository, Mockito.times(1)).findRoleByUserRoles(UserRoles.OWNER);
    }

    @Test
    void getRoleById_should_call_roleRepository_findById_once() {
        Mockito.when(roleRepository.findById(1)).thenReturn(Optional.of(new Role()));
        roleService.getRoleById(1);
        Mockito.verify(roleRepository, Mockito.times(1)).findById(1);
    }
}
