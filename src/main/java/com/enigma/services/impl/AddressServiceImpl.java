package com.enigma.services.impl;

import com.enigma.constans.ResponseMessageLocation;
import com.enigma.entities.Address;
import com.enigma.exceptions.NotFoundException;
import com.enigma.repositories.AddressRepository;
import com.enigma.services.AddressService;
import com.enigma.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    LocationService locationService;

    @Override
    public List<Address> findAll() {
        return this.addressRepository.findAll();
    }

    @Override
    public Address saveAddress(Address address) {
        return null;
    }

    @Override
    public Address findAddressById(String id) {
        if (!(this.addressRepository.findById(id).isPresent()))
            throw new NotFoundException(ResponseMessageLocation.FAILED_GET_LOCATION);
        return this.addressRepository.findById(id).get();
    }

    @Override
    public Address updateAddress(Address address) {
        findAddressById(address.getId());
        return this.saveAddress(address);
    }

    @Override
    public void deleteAddressById(String id) {
        this.addressRepository.delete(findAddressById(id));
    }
}
