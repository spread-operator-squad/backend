package com.enigma.services;

import com.enigma.entities.Address;

import java.util.List;

public interface AddressService {
    List<Address> findAll();
    Address saveAddress(Address address);
    Address findAddressById(String id);
    Address updateAddress(Address address);
    void deleteAddressById(String id);
}
