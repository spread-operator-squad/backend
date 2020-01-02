package com.enigma.services.impl;

import com.enigma.constans.ResponseMessageAddress;
import com.enigma.entities.Address;
import com.enigma.exceptions.NotFoundException;
import com.enigma.repositories.AddressRepository;
import com.enigma.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<Address> findAll() {
        return this.addressRepository.findAll();
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address findAddressById(String id) {
        if (!(this.addressRepository.findById(id).isPresent()))
            throw new NotFoundException(ResponseMessageAddress.FAILED_GET_ADDRESS);
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
