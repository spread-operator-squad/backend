package com.enigma.customerExperience.impl;

import com.enigma.entities.CustomerExperience;
import com.enigma.repositories.CustomerExperienceRepository;
import com.enigma.services.CustomerExperienceService;
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
public class CustomerExperienceImplTest {

    @MockBean
    CustomerExperienceRepository customerExperienceRepository;

    @SpyBean
    CustomerExperienceService customerExperienceService;

    @Test
    void findAll_should_call_customerExperienceRepository_findAll_once(){
        customerExperienceService.findAll();
        Mockito.verify(customerExperienceRepository, Mockito.times(1)).findAll();
    }

    @Test
    void saveCustomerExperience_should_call_customerExperienceRepository_save_once() {
        CustomerExperience customer = new CustomerExperience();
        customer.setId(1);
        customer.setLevel("master");
        customer.setPoint(50);
        customerExperienceService.saveCustomerExperience(customer);
        Mockito.verify(customerExperienceRepository, Mockito.times(1)).save(customer);
    }

    @Test
    void findCustomerExperienceById_should_call_customerExperienceRepository_findById_twice() {
        CustomerExperience customer = new CustomerExperience();
        customer.setId(1);
        customer.setLevel("master");
        customer.setPoint(50);
        Mockito.when(customerExperienceRepository.findById(1)).thenReturn(Optional.of(customer));
        customerExperienceService.findCustomerExperienceById(1);
        Mockito.verify(customerExperienceRepository, Mockito.times(2)).findById(1);
    }

    @Test
    void updateCustomerExperience_should_call_customerExperienceService_findCustomerExperienceById_once() {
        CustomerExperience customer = new CustomerExperience();
        customer.setId(1);
        customer.setLevel("master");
        customer.setPoint(50);
        Mockito.when(customerExperienceRepository.findById(1)).thenReturn(Optional.of(customer));
        customerExperienceService.findCustomerExperienceById(1);
        Mockito.verify(customerExperienceService, Mockito.times(1)).findCustomerExperienceById(1);
    }

    @Test
    void updateCustomerExperience_should_call_customerExperienceService_save_once() {
        CustomerExperience customer = new CustomerExperience();
        customer.setId(1);
        customer.setLevel("master");
        customer.setPoint(50);
        Mockito.when(customerExperienceRepository.findById(1)).thenReturn(Optional.of(customer));
        Mockito.when(customerExperienceService.saveCustomerExperience(customer)).thenReturn(customer);
        customerExperienceService.updateCustomerExperience(customer);
        Mockito.verify(customerExperienceService, Mockito.times(1)).updateCustomerExperience(customer);
    }

    @Test
    void deleteCustomerExperienceById_should_call_customerExperienceRepository_delete_once() {
        CustomerExperience customer = new CustomerExperience();
        customer.setId(1);
        customer.setLevel("master");
        customer.setPoint(50);
        Mockito.when(customerExperienceRepository.findById(1)).thenReturn(Optional.of(customer));
        customerExperienceService.deleteCustomerExperienceById((1));
        Mockito.verify(customerExperienceRepository, Mockito.times(1)).delete(customer);
    }

}
