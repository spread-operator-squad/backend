package com.enigma.services.impl;

import com.enigma.constans.ResponseMessageAddress;
import com.enigma.entities.Address;
import com.enigma.entities.Location;
import com.enigma.repositories.AddressRepository;
import com.enigma.services.AddressService;
import com.enigma.services.LocationService;
import com.enigma.services.impl.CustomResponse;
import com.enigma.services.impl.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    LocationService locationService;

    @Override
    public CustomResponse findAll(){
        return new CustomResponse(new Status(HttpStatus.OK, ResponseMessageAddress.SUCCESS_GET_ADDRESSAll), this.addressRepository.findAll());
    }

    @Override
    public CustomResponse saveAddress(Address address){
        if (address.getLocationId() != null) address.setLocation((Location) locationService.findLocationById(address.getLocationId()).getData());
        return new CustomResponse(new Status(HttpStatus.CREATED,ResponseMessageAddress.SUCCESS_SAVE_ADDRESS), this.addressRepository.save(address));
    }

    @Override
    public CustomResponse findAddressById(String id){
        if (!(this.addressRepository.findById(id).isPresent())) return new CustomResponse(new Status(HttpStatus.NOT_FOUND, "Address is not found"));
        return new CustomResponse(new Status(HttpStatus.OK, ResponseMessageAddress.SUCCESS_GET_ADDRESS), this.addressRepository.findById(id).get());
    }

    @Override
    public CustomResponse updateAddress(Address address){
        if (this.findAddressById(address.getId()).getStatus().getCode().equals(HttpStatus.NOT_FOUND.value())) return this.findAddressById(address.getId());
        else return new CustomResponse(new Status(HttpStatus.OK,ResponseMessageAddress.SUCCESS_UPDATE_ADDRESS), this.saveAddress(address).getData());
    }

    @Override
    public CustomResponse deleteAddressById(String id) {
        this.addressRepository.delete((Address) this.findAddressById(id).getData());
        return new CustomResponse(new Status(HttpStatus.NO_CONTENT,ResponseMessageAddress.SUCCESS_DELETE_ADDRESS));
    }
}
