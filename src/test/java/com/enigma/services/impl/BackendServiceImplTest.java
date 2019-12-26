package com.enigma.services.impl;

import com.enigma.entities.BackendService;
import com.enigma.entities.Item;
import com.enigma.repositories.BackendServiceRepository;
import com.enigma.repositories.ItemRepository;
import com.enigma.services.BackendServicesService;
import com.enigma.services.ItemService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BackendServiceImplTest {

    @MockBean
    BackendServiceRepository backendServiceRepository;

    @SpyBean
    BackendServicesService backendServicesService;

    @Test
    void findAll_should_call_backendServiceRepository_findAll_once(){
        backendServicesService.findAll();
        Mockito.verify(backendServiceRepository, Mockito.times(1)).findAll();
    }

    @Test
    void saveBackendService_should_call_backendServiceRepository_save_once() {
        BackendService backendService1 = new BackendService();
        backendService1.setCode("code");
        backendService1.setEndPoint("end point");
        backendService1.setHttpMethod("http method");
        backendServicesService.saveBackendService(backendService1);
        Mockito.verify(backendServiceRepository, Mockito.times(1)).save(backendService1);
    }

    @Test
    void findBackendServiceById_should_call_backendServiceRepository_findById_twice() {
        BackendService backendService1 = new BackendService();
        backendService1.setId(1);
        backendService1.setCode("code");
        backendService1.setEndPoint("end point");
        backendService1.setHttpMethod("http method");
        Mockito.when(backendServiceRepository.findById(1)).thenReturn(Optional.of(backendService1));
        backendServicesService.findBackendServiceById(1);
        Mockito.verify(backendServiceRepository, Mockito.times(2)).findById(1);
    }

    @Test
    void updateBackendService_should_call_backendService_findUserById_once() {
        BackendService backendService1 = new BackendService();
        backendService1.setId(1);
        backendService1.setCode("code");
        backendService1.setEndPoint("end point");
        backendService1.setHttpMethod("http method");
        Mockito.when(backendServiceRepository.findById(1)).thenReturn(Optional.of(backendService1));
        backendServicesService.findBackendServiceById(1);
        Mockito.verify(backendServicesService, Mockito.times(1)).findBackendServiceById(1);
    }

    @Test
    void updateBackendService_should_call_backendServicesService_save_once() {
        BackendService backendService1 = new BackendService();
        backendService1.setId(1);
        backendService1.setCode("code");
        backendService1.setEndPoint("end point");
        backendService1.setHttpMethod("http method");
        Mockito.when(backendServiceRepository.findById(1)).thenReturn(Optional.of(backendService1));
        Mockito.when(backendServicesService.saveBackendService(backendService1)).thenReturn(backendService1);
        backendServicesService.updateBackendService(backendService1);
        Mockito.verify(backendServicesService, Mockito.times(1)).updateBackendService(backendService1);
    }

    @Test
    void deleteBackendServiceById_should_call_backendServiceRepository_delete_once() {
        BackendService backendService1 = new BackendService();
        backendService1.setId(1);
        backendService1.setCode("code");
        backendService1.setEndPoint("end point");
        backendService1.setHttpMethod("http method");
        Mockito.when(backendServiceRepository.findById(1)).thenReturn(Optional.of(backendService1));
        backendServicesService.deleteBackendServiceById((1));
        Mockito.verify(backendServiceRepository, Mockito.times(1)).delete(backendService1);
    }
}
