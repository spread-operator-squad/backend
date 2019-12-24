package com.enigma.services.impl;

import com.enigma.constans.ResponseMessageCustomerExperience;
import com.enigma.entities.CustomerExperience;
import com.enigma.exceptions.NotFoundException;
import com.enigma.repositories.CustomerExperienceRepository;
import com.enigma.services.CustomerExperienceService;
import com.enigma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerExperienceServiceImpl implements CustomerExperienceService {

    @Autowired
    CustomerExperienceRepository customerExperienceRepository;

    @Autowired
    UserService userService;

    @Override
    public List<CustomerExperience> findAll() {
        return customerExperienceRepository.findAll();
    }

    @Override
    public CustomerExperience saveCustomerExperience(CustomerExperience customerExperience) {
        if (!(customerExperience.getUserId().isEmpty())) customerExperience.setUser(userService.findUserById(customerExperience.getUserId()));
        return customerExperienceRepository.save(customerExperience);
    }

    @Override
    public CustomerExperience findCustomerExperienceById(Integer id) {
        if (!(this.customerExperienceRepository.findById(id).isPresent())) throw new NotFoundException(ResponseMessageCustomerExperience.FAILED_GET_CUSTOMER_EXPERIENCE);
        return this.customerExperienceRepository.findById(id).get();
    }

    @Override
    public CustomerExperience updateCustomerExperience(CustomerExperience customerExperience) {
        this.findCustomerExperienceById(customerExperience.getId());
        return this.saveCustomerExperience(customerExperience);
    }

    @Override
    public void deleteCustomerExperienceById(Integer id) {
        this.customerExperienceRepository.delete(this.findCustomerExperienceById(id));
    }
}
