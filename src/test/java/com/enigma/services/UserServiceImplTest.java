package com.enigma.services;

import com.enigma.entities.User;
import com.enigma.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceImplTest {

    @MockBean
    UserRepository userRepository;

    @SpyBean
    UserService userService;

    @Test
    void findAll_should_call_userRepository_findALl_once(){
        userService.findAll();
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    void saveUser_should_call_userRepository_save_once() {
        User sampleUser = new User();
        sampleUser.setUsername("jhon");
        sampleUser.setPassword("thor");
        userService.saveUser(sampleUser);
        Mockito.verify(userRepository, Mockito.times(1)).save(sampleUser);
    }

    @Test
    void findUserById_should_call_userRepository_findById_once() {
        userService.findUserById("id");
        Mockito.verify(userRepository, Mockito.times(1)).findById("id");
    }

    @Test
    void updateUser_should_call_userService_findUserById_once() {
        User sampleUser = new User();
        sampleUser.setId("id");
        sampleUser.setUsername("jhon");
        sampleUser.setPassword("thor");
        userService.updateUser(sampleUser);
        Mockito.verify(userService, Mockito.times(2)).findUserById("id");
    }

    @Test
    void updateUser_should_call_userService_save_once() {
        User sampleUser = new User();
        sampleUser.setId("id");
        sampleUser.setUsername("jhon");
        sampleUser.setPassword("thor");

        userService.updateUser(sampleUser);
        Mockito.verify(userService, Mockito.times(1)).saveUser(sampleUser);
    }

    @Test
    void deleteUserById() {
    }
}
