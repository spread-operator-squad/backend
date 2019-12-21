package com.enigma.services.impl;

import com.enigma.constans.ResponseMessageCustomerExperience;
import com.enigma.entities.CustomerExperience;
import com.enigma.entities.User;
import com.enigma.repositories.CustomerExperienceRepository;
import com.enigma.services.CustomerExperienceService;
import com.enigma.services.UserService;
import com.enigma.services.impl.CustomResponse;
import com.enigma.services.impl.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CustomerExperienceServiceImpl implements CustomerExperienceService {

    @Autowired
    CustomerExperienceRepository customerExperienceRepository;

    @Autowired
    UserService userService;

    @Override
    public CustomResponse findAll() {
        return new CustomResponse(new Status(HttpStatus.OK, ResponseMessageCustomerExperience.SUCCESS_GET_CUSTOMER_EXPERIENCES), this.customerExperienceRepository.findAll());
    }

    @Override
    public CustomResponse saveCustomerExperience(CustomerExperience customerExperience) {
        if (customerExperience.getUserId() != null) customerExperience.setUser((User) userService.findUserById(customerExperience.getUserId()).getData());
        return new CustomResponse(new Status(HttpStatus.CREATED, ResponseMessageCustomerExperience.SUCCESS_SAVE_CUSTOMER_EXPERIENCE), this.customerExperienceRepository.save(customerExperience));
    }

    @Override
    public CustomResponse findCustomerExperienceById(Integer id) {
        if (!(this.customerExperienceRepository.findById(id).isPresent())) return new CustomResponse(new Status(HttpStatus.NOT_FOUND, "Customer Experience is not found"));
        return new CustomResponse(new Status(HttpStatus.OK, ResponseMessageCustomerExperience.SUCCESS_GET_CUSTOMER_EXPERIENCE), this.customerExperienceRepository.findById(id).get());
    }

    @Override
    public CustomResponse updateCustomerExperience(CustomerExperience customerExperience) {
        if (this.findCustomerExperienceById(customerExperience.getId()).getStatus().getCode().equals(HttpStatus.NOT_FOUND.value())) return this.findCustomerExperienceById(customerExperience.getId());
        else return new CustomResponse(new Status(HttpStatus.OK, ResponseMessageCustomerExperience.SUCCESS_UPDATE_CUSTOMER_EXPERIENCE), this.saveCustomerExperience(customerExperience).getData());
    }

    @Override
    public CustomResponse deleteCustomerExperienceById(Integer id) {
        this.customerExperienceRepository.delete((CustomerExperience) this.findCustomerExperienceById(id).getData());
        return new CustomResponse(new Status(HttpStatus.NO_CONTENT, ResponseMessageCustomerExperience.SUCCESS_DELETE_CUSTOMER_EXPERIENCE));
    }
}
