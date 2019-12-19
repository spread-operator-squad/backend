package com.enigma.services;

import com.enigma.entities.Address;

public interface AddressService {
    CustomResponse findAll();
    CustomResponse saveAddress(Address address);
    CustomResponse findAddressById(String id);
    CustomResponse updateAddress(Address address);
    CustomResponse deleteAddressById(String id);
}
