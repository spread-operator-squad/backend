package com.enigma.services.impl;

import com.enigma.entities.Item;
import com.enigma.entities.Store;
import com.enigma.repositories.ItemRepository;
import com.enigma.services.ItemService;
import com.enigma.services.StoreService;
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
public class ItemServiceImplTest {

    @MockBean
    ItemRepository itemRepository;

    @MockBean
    StoreService storeService;

    @SpyBean
    ItemService itemService;

    @Test
    void findAll_should_call_itemRepository_findAll_once(){
        itemService.findAllItem();
        Mockito.verify(itemRepository, Mockito.times(1)).findAll();
    }

    @Test
    void saveItem_should_call_itemRepository_save_once() {
        Item item1 = new Item();
        item1.setName("Pakaian");
        item1.setPrice(new BigDecimal(7000));
        itemService.saveItem(item1);
        Mockito.verify(itemRepository, Mockito.times(1)).save(item1);
    }

    @Test
    void findItemById_should_call_itemRepository_findById_twice() {
        Item item1 = new Item();
        item1.setId(1);
        item1.setName("Pakaian");
        item1.setPrice(new BigDecimal(7000));
        Mockito.when(itemRepository.findById(1)).thenReturn(Optional.of(item1));
        itemService.findItemById(1);
        Mockito.verify(itemRepository, Mockito.times(2)).findById(1);
    }

    @Test
    void updateUser_should_call_userService_findUserById_once() {
        Item item1 = new Item();
        item1.setId(1);
        item1.setName("Pakaian");
        item1.setPrice(new BigDecimal(7000));
        Mockito.when(itemRepository.findById(1)).thenReturn(Optional.of(item1));
        itemService.findItemById(1);
        Mockito.verify(itemService, Mockito.times(1)).findItemById(1);
    }

    @Test
    void updateItem_should_call_itemService_save_once() {
        Item item1 = new Item();
        item1.setId(1);
        item1.setName("Pakaian");
        item1.setPrice(new BigDecimal(7000));
        Mockito.when(itemRepository.findById(1)).thenReturn(Optional.of(item1));
        Mockito.when(itemService.saveItem(item1)).thenReturn(item1);
        itemService.updateItem(item1);
        Mockito.verify(itemService, Mockito.times(1)).updateItem(item1);
    }

    @Test
    void deleteUserById_should_call_userRepository_delete_once() {
        Item item1 = new Item();
        item1.setId(1);
        item1.setName("Pakaian");
        item1.setPrice(new BigDecimal(7000));
        Mockito.when(itemRepository.findById(1)).thenReturn(Optional.of(item1));
        itemService.deleteItem((1));
        Mockito.verify(itemRepository, Mockito.times(1)).delete(item1);
    }

    @Test
    void findAllItemByIdStore_should_call_storeRepository_once() {
        Mockito.when(storeService.findStoreById(1)).thenReturn(new Store());
        itemService.findAllItemByIdStore(1);
        Mockito.verify(storeService, Mockito.times(1)).findStoreById(1);
    }
}
