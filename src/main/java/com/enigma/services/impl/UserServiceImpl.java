package com.enigma.services.impl;

import com.enigma.constans.ResponseMessageUser;
import com.enigma.entities.Role;
import com.enigma.entities.User;
import com.enigma.enumeration.UserRoles;
import com.enigma.repositories.RoleRepository;
import com.enigma.repositories.UserRepository;
import com.enigma.services.RoleService;
import com.enigma.services.UserService;
import com.enigma.services.impl.CustomResponse;
import com.enigma.services.impl.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleService roleService;

    @Override
    public CustomResponse findAll(){
        return new CustomResponse(new Status(HttpStatus.OK, ResponseMessageUser.SUCCESS_GET_USERS), this.userRepository.findAll());
    }

    @Override
    public CustomResponse saveUser(User user){
        if (!(user.getRoles().isEmpty())) user.setUserRoles(userHasRole(user));
        if (user.getUserDetail() != null) user.getUserDetail().setUser(user);
        return new CustomResponse(new Status(HttpStatus.CREATED, ResponseMessageUser.SUCCESS_SAVE_USER), this.userRepository.save(user));
    }

    private Set<Role> userHasRole(User user) {
        Set<Role> roles = new HashSet<>();
        if (!(user.getRoles().isEmpty())) {
            for (String role:user.getRoles()){
                roles.add(roleService.getRoleByRoles(UserRoles.getUserRoleByLabel(role)));
            }
        }
        return roles;
    }

    @Override
    public CustomResponse findUserById(String id){
        if (!(this.userRepository.findById(id).isPresent())) return new CustomResponse(new Status(HttpStatus.NOT_FOUND, "User is not found"));
        return new CustomResponse(new Status(HttpStatus.OK, ResponseMessageUser.SUCCESS_GET_USER), this.userRepository.findById(id).get());
    }

    @Override
    public CustomResponse updateUser(User user){
        if (this.findUserById(user.getId()).getStatus().getCode().equals(HttpStatus.NOT_FOUND.value())) return this.findUserById(user.getId());
        else return new CustomResponse(new Status(HttpStatus.OK, ResponseMessageUser.SUCCESS_UPDATE_USER), this.saveUser(user).getData());
    }

    @Override
    public CustomResponse deleteUserById(String id) {
        this.userRepository.delete((User) this.findUserById(id).getData());
        return new CustomResponse(new Status(HttpStatus.NO_CONTENT, ResponseMessageUser.SUCCESS_DELETE_USER));
    }

    @Override
    public CustomResponse blockUserById(String id) {
        User userFound = (User) this.findUserById(id).getData();
        userFound.setIsActive(false);
        return new CustomResponse(new Status(HttpStatus.OK, String.format(ResponseMessageUser.SUCCESS_BLOCK_USER, userFound.getUsername())), updateUser(userFound));
    }
}
