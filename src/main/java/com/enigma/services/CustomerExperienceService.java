package com.enigma.services;

import com.enigma.entities.CustomerExperience;

import java.util.List;

public interface CustomerExperienceService {
    List<CustomerExperience> findAll();
    CustomerExperience saveCustomerExperience(CustomerExperience customerExperience);
    CustomerExperience findCustomerExperienceById(Integer id);
    CustomerExperience updateCustomerExperience(CustomerExperience customerExperience);
    void deleteCustomerExperienceById(Integer id);
}
