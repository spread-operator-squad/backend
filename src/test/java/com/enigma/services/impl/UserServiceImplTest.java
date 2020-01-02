package com.enigma.services.impl;

import com.enigma.entities.User;
import com.enigma.repositories.UserRepository;
import com.enigma.services.UserService;
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
    void findUserById_should_call_userRepository_findById_twice() {
        User sampleUser = new User();
        sampleUser.setId("id");
        sampleUser.setUsername("jhon");
        sampleUser.setPassword("thor");
        Mockito.when(userRepository.findById("id")).thenReturn(Optional.of(sampleUser));
        userService.findUserById("id");
        Mockito.verify(userRepository, Mockito.times(2)).findById("id");
    }

    @Test
    void updateUser_should_call_userService_findUserById_once() {
        User sampleUser = new User();
        sampleUser.setId("id");
        sampleUser.setUsername("jhon");
        sampleUser.setPassword("thor");
        Mockito.when(userRepository.findById("id")).thenReturn(Optional.of(sampleUser));
        userService.updateUser(sampleUser);
        Mockito.verify(userService, Mockito.times(1)).findUserById("id");
    }

    @Test
    void updateUser_should_call_userService_save_once() {
        User sampleUser = new User();
        sampleUser.setId("id");
        sampleUser.setUsername("jhon");
        sampleUser.setPassword("thor");
        Mockito.when(userRepository.findById("id")).thenReturn(Optional.of(sampleUser));
        Mockito.when(userService.saveUser(sampleUser)).thenReturn(sampleUser);
        userService.updateUser(sampleUser);
        Mockito.verify(userService, Mockito.times(1)).updateUser(sampleUser);
    }

    @Test
    void deleteUserById_should_call_userRepository_delete_once() {
        User sampleUser = new User();
        sampleUser.setId("id");
        sampleUser.setUsername("jhon");
        sampleUser.setPassword("thor");
        Mockito.when(userRepository.findById("id")).thenReturn(Optional.of(sampleUser));
        userService.deleteUserById("id");
        Mockito.verify(userRepository, Mockito.times(1)).delete(sampleUser);
    }

    @Test
    void blockUserById_should_return_isActive_false() {
        User sampleUser = new User();
        sampleUser.setId("id");
        sampleUser.setIsActive(false);
        sampleUser.setUsername("jhon");
        sampleUser.setPassword("thor");

        User actualUser = new User();
        actualUser.setId("id");
        actualUser.setUsername("jhon");
        actualUser.setPassword("thor");
        actualUser.setIsActive(false);
        Mockito.when(userRepository.findById("id")).thenReturn(Optional.of(sampleUser));
        Mockito.when(userService.blockUserById("id")).thenReturn(actualUser);
        assertEquals(userService.blockUserById("id"), actualUser);
    }
}
