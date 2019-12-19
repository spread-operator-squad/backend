package com.enigma.services;

import com.enigma.entities.CustomerExperience;

public interface CustomerExperienceService {
    CustomResponse findAll();
    CustomResponse saveCustomerExperience(CustomerExperience customerExperience);
    CustomResponse findCustomerExperienceById(Integer id);
    CustomResponse updateCustomerExperience(CustomerExperience customerExperience);
    CustomResponse deleteCustomerExperienceById(Integer id);
}
