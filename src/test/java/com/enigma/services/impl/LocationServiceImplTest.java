package com.enigma.services.impl;

import com.enigma.entities.Item;
import com.enigma.entities.Location;
import com.enigma.repositories.ItemRepository;
import com.enigma.repositories.LocationRepository;
import com.enigma.services.ItemService;
import com.enigma.services.LocationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LocationServiceImplTest {

    @MockBean
    LocationRepository locationRepository;

    @SpyBean
    LocationService locationService;

    @Test
    void findAll_should_call_locationRepository_findAll_once(){
        locationService.findAll();
        Mockito.verify(locationRepository, Mockito.times(1)).findAll();
    }

    @Test
    void saveLocation_should_call_locationRepository_save_once() {
        Location location1 = new Location();
        location1.setId(1);
        location1.setName("location 1");
        location1.setType("location type");
        locationService.saveLocation(location1);
        Mockito.verify(locationRepository, Mockito.times(1)).save(location1);
    }

    @Test
    void findLocationById_should_call_locationRepository_findById_twice() {
        Location location1 = new Location();
        location1.setId(1);
        location1.setName("location 1");
        location1.setType("location type");
        Mockito.when(locationRepository.findById(1)).thenReturn(Optional.of(location1));
        locationService.findLocationById(1);
        Mockito.verify(locationRepository, Mockito.times(2)).findById(1);
    }

    @Test
    void findLocationByType_should_Call_locationRepository_findByTypeContainingIgnoreCase_twice() {
        Mockito.when(locationRepository.findByTypeContainingIgnoreCase("City")).thenReturn(new Location());
        locationService.findLocationByType("City");
        Mockito.verify(locationRepository, Mockito.times(2)).findByTypeContainingIgnoreCase("City");
    }

    @Test
    void updateLocation_should_call_locationService_findLocationById_once() {
        Location location1 = new Location();
        location1.setId(1);
        location1.setName("location 1");
        location1.setType("location type");
        Mockito.when(locationRepository.findById(1)).thenReturn(Optional.of(location1));
        locationService.findLocationById(1);
        Mockito.verify(locationService, Mockito.times(1)).findLocationById(1);
    }

    @Test
    void updateLocation_should_call_locationService_save_once() {
        Location location1 = new Location();
        location1.setId(1);
        location1.setName("location 1");
        location1.setType("location type");
        Mockito.when(locationRepository.findById(1)).thenReturn(Optional.of(location1));
        Mockito.when(locationService.saveLocation(location1)).thenReturn(location1);
        locationService.updateLocation(location1);
        Mockito.verify(locationService, Mockito.times(1)).updateLocation(location1);
    }

    @Test
    void deleteLocationById_should_call_locationRepository_delete_once() {
        Location location1 = new Location();
        location1.setId(1);
        location1.setName("location 1");
        location1.setType("location type");
        Mockito.when(locationRepository.findById(1)).thenReturn(Optional.of(location1));
        locationService.deleteLocationById((1));
        Mockito.verify(locationRepository, Mockito.times(1)).delete(location1);
    }
}
