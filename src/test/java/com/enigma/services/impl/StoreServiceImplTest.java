package com.enigma.services.impl;

import com.enigma.constans.ResponseMessageStore;
import com.enigma.entities.Store;
import com.enigma.exceptions.NotFoundException;
import com.enigma.repositories.StoreRepository;
import com.enigma.services.StoreService;
import com.enigma.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.awt.image.SampleModel;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class StoreServiceImplTest {

    @MockBean
    StoreRepository storeRepository;

    @MockBean
    UserService userService;

    @SpyBean
    StoreService storeService;

    @Test
    void findAllStore_should_call_storeRepository_once() {
        storeService.findAllStore();
        Mockito.verify(storeRepository, Mockito.times(1)).findAll();
    }

    @Test
    void saveStore_should_call_userService_once_when_ownerId_not_null() {
        Store sampleStore = new Store();
        sampleStore.setId(1);
        sampleStore.setName("Sample");
        sampleStore.setOwnerId("id");
        storeService.saveStore(sampleStore);
        Mockito.verify(userService, Mockito.times(1)).findUserById("id");
    }

    @Test
    void saveStore_should_call_userRepository_save_once(){
        Store sampleStore = new Store();
        sampleStore.setId(1);
        sampleStore.setName("Sample");
        sampleStore.setOwnerId("id");
        storeService.saveStore(sampleStore);
        Mockito.verify(storeRepository, Mockito.times(1)).save(sampleStore);
    }

    @Test
    void findStoreById_should_call_userRepository_findById_once() {
        Store sampleStore = new Store();
        sampleStore.setId(1);
        sampleStore.setName("Sample");
        sampleStore.setOwnerId("id");
        Mockito.when(storeRepository.findById(1)).thenReturn(Optional.of(sampleStore));
        storeService.findStoreById(1);
        Mockito.verify(storeService, Mockito.times(1)).findStoreById(1);
    }

    @Test
    void findStoreById_should_return_notFoundException_when_id_doesnt_exist_in_db() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
           storeService.findStoreById(1);
        });

        String expectedMessage = ResponseMessageStore.FAILED_GET_STORE;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void updateStore_should_call_storeService_findStoreById_once() {
        Store sampleStore = new Store();
        sampleStore.setId(1);
        sampleStore.setName("Sample");
        sampleStore.setOwnerId("id");
        Mockito.when(storeRepository.findById(1)).thenReturn(Optional.of(sampleStore));
        storeService.updateStore(sampleStore);
        Mockito.verify(storeService, Mockito.times(1)).findStoreById(1);
    }

    @Test
    void updateStore_should_call_storeService_saveStore_once() {
        Store sampleStore = new Store();
        sampleStore.setId(1);
        sampleStore.setName("Sample");
        sampleStore.setOwnerId("id");
        Mockito.when(storeRepository.findById(1)).thenReturn(Optional.of(sampleStore));
        storeService.updateStore(sampleStore);
        Mockito.verify(storeService, Mockito.times(1)).saveStore(sampleStore);
    }

    @Test
    void deleteStore_should_call_storeService_findStoreById_once() {
        Store sampleStore = new Store();
        sampleStore.setId(1);
        sampleStore.setName("Sample");
        sampleStore.setOwnerId("id");
        Mockito.when(storeRepository.findById(1)).thenReturn(Optional.of(sampleStore));
        storeService.deleteStore(1);
        Mockito.verify(storeService, Mockito.times(1)).findStoreById(1);
    }

    @Test
    void deleteStore_should_call_userRepository_delete_once() {
        Store sampleStore = new Store();
        sampleStore.setId(1);
        sampleStore.setName("Sample");
        sampleStore.setOwnerId("id");
        Mockito.when(storeRepository.findById(1)).thenReturn(Optional.of(sampleStore));
        storeService.deleteStore(1);
        Mockito.verify(storeRepository, Mockito.times(1)).delete(sampleStore);
    }
}
