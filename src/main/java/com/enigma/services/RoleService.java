package com.enigma.services;

import com.enigma.entities.Role;
import com.enigma.enumeration.UserRoles;

public interface RoleService {
    Role getRoleByRoles(UserRoles role);
    Role getRoleById(Integer id);
}
