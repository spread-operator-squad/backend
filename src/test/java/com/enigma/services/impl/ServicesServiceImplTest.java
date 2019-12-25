package com.enigma.services.impl;

import com.enigma.entities.Services;
import com.enigma.entities.Store;
import com.enigma.repositories.ServiceRepository;
import com.enigma.services.ServicesService;
import com.enigma.services.StoreService;
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
class ServicesServiceImplTest {

    @MockBean
    ServiceRepository serviceRepository;

    @MockBean
    StoreService storeService;

    @SpyBean
    ServicesService servicesService;

    @Test
    void findAll_should_call_serviceRepository_findAll_once() {
        servicesService.findAll();
        Mockito.verify(serviceRepository, Mockito.times(1)).findAll();
    }

    @Test
    void saveServices_should_call_serviceRepository_save_once() {
        Services sample = new Services();
        sample.setName("Lorem Ipsum");
        sample.setStoreId(1);
        Mockito.when(storeService.findStoreById(1)).thenReturn(new Store());
        servicesService.saveServices(sample);
        Mockito.verify(servicesService, Mockito.times(1)).saveServices(sample);
    }

    @Test
    void findServicesById_should_call_serviceRepository_findById_twice() {
        Services sample = new Services();
        sample.setName("Lorem Ipsum");
        sample.setStoreId(1);
        Mockito.when(serviceRepository.findById(1)).thenReturn(Optional.of(sample));
        servicesService.findServicesById(1);
        Mockito.verify(serviceRepository, Mockito.times(2)).findById(1);
    }

    @Test
    void updateServices_should_call_servicesService_findServiceById_once() {
        Services sample = new Services();
        sample.setId(1);
        sample.setName("Lorem Ipsum");
        sample.setStoreId(1);
        Mockito.when(serviceRepository.findById(1)).thenReturn(Optional.of(sample));
        servicesService.updateServices(sample);
        Mockito.verify(servicesService, Mockito.times(1)).findServicesById(1);
    }

    @Test
    void updateServices_should_call_servicesService_saveService_once() {
        Services sample = new Services();
        sample.setId(1);
        sample.setName("Lorem Ipsum");
        sample.setStoreId(1);
        Mockito.when(serviceRepository.findById(1)).thenReturn(Optional.of(sample));
        servicesService.updateServices(sample);
        Mockito.verify(servicesService, Mockito.times(1)).saveServices(sample);
    }

    @Test
    void deleteServices_should_call_serviceRepository_delete_once() {
        Services sample = new Services();
        sample.setId(1);
        sample.setName("Lorem Ipsum");
        sample.setStoreId(1);
        Mockito.when(serviceRepository.findById(1)).thenReturn(Optional.of(sample));
        servicesService.deleteServices(1);
        Mockito.verify(servicesService, Mockito.times(1)).deleteServices(1);
    }
}
