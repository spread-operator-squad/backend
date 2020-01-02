package com.enigma.services.impl;

import com.enigma.entities.Address;
import com.enigma.repositories.AddressRepository;
import com.enigma.services.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AddressServiceImplTest {

    @MockBean
    AddressRepository addressRepository;

    @SpyBean
    AddressService addressService;

    @Test
    void findAll_should_call_addressRepository_findAll_once(){
        addressService.findAll();
        Mockito.verify(addressRepository, Mockito.times(1)).findAll();
    }

    @Test
    void saveAddress_should_call_addressRepository_save_once() {
        Address address1 = new Address();
        address1.setDescription("Ragunan");
        address1.setLatitude("latitude");
        address1.setLongitude("longtitude");
        addressService.saveAddress(address1);
        Mockito.verify(addressRepository, Mockito.times(1)).save(address1);
    }

    @Test
    void findAddressById_should_call_addressRepository_findById_twice() {
        Address address1 = new Address();
        address1.setId("id");
        address1.setDescription("Ragunan");
        address1.setLatitude("latitude");
        address1.setLongitude("longtitude");
        Mockito.when(addressRepository.findById("id")).thenReturn(Optional.of(address1));
        addressService.findAddressById("id");
        Mockito.verify(addressRepository, Mockito.times(2)).findById("id");
    }

    @Test
    void updateAddress_should_call_addressService_findAddressById_once() {
        Address address1 = new Address();
        address1.setId("id");
        address1.setDescription("Ragunan");
        address1.setLatitude("latitude");
        address1.setLongitude("longtitude");
        Mockito.when(addressRepository.findById("id")).thenReturn(Optional.of(address1));
        addressService.findAddressById("id");
        Mockito.verify(addressService, Mockito.times(1)).findAddressById("id");
    }

    @Test
    void updateAddress_should_call_addressService_save_once() {
        Address address1 = new Address();
        address1.setId("id");
        address1.setDescription("Ragunan");
        address1.setLatitude("latitude");
        address1.setLongitude("longtitude");
        Mockito.when(addressRepository.findById("id")).thenReturn(Optional.of(address1));
        Mockito.when(addressService.saveAddress(address1)).thenReturn(address1);
        addressService.updateAddress(address1);
        Mockito.verify(addressService, Mockito.times(1)).updateAddress(address1);
    }

    @Test
    void deleteAddressById_should_call_addressRepository_delete_once() {
        Address address1 = new Address();
        address1.setId("id");
        address1.setDescription("Ragunan");
        address1.setLatitude("latitude");
        address1.setLongitude("longtitude");
        Mockito.when(addressRepository.findById("id")).thenReturn(Optional.of(address1));
        addressService.deleteAddressById("id");
        Mockito.verify(addressRepository, Mockito.times(1)).delete(address1);
    }

}
