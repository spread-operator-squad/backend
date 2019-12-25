package com.enigma.services.impl;

import com.enigma.constans.ResponseMessageUser;
import com.enigma.entities.Role;
import com.enigma.entities.User;
import com.enigma.enumeration.UserRoles;
import com.enigma.exceptions.NotFoundException;
import com.enigma.repositories.RoleRepository;
import com.enigma.repositories.UserRepository;
import com.enigma.services.RoleService;
import com.enigma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleService roleService;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        if (!(user.getRoles().isEmpty())) user.setUserRoles(userHasRole(user));
        if (user.getUserDetail() != null) user.getUserDetail().setUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    public Set<Role> userHasRole(User user) {
        Set<Role> roles = new HashSet<>();
        if (!(user.getRoles().isEmpty())) {
            for (String role : user.getRoles()) {
                roles.add(roleService.getRoleByRoles(UserRoles.getUserRoleByLabel(role)));
            }
        }
        return roles;
    }

    @Override
    public User findUserById(String id) {
        if (!(this.userRepository.findById(id).isPresent())) throw new NotFoundException(ResponseMessageUser.FAILED_GET_USER);
        return this.userRepository.findById(id).get();
    }

    @Override
    public User updateUser(User user) {
        this.findUserById(user.getId());
        return this.saveUser(user);
    }

    @Override
    public void deleteUserById(String id) {
        this.userRepository.delete(this.findUserById(id));
    }

    @Override
    public User blockUserById(String id) {
        User userFound = this.findUserById(id);
        userFound.setIsActive(false);
        return this.updateUser(userFound);
    }
}
