package com.api.ecmpdev.services;

import com.api.ecmpdev.AbstractContainerBaseTest;
import com.api.ecmpdev.dtos.ResponseItem;
import com.api.ecmpdev.enums.Types;
import com.api.ecmpdev.models.Item;
import com.api.ecmpdev.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.junit.jupiter.api.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceTest extends AbstractContainerBaseTest {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    PasswordEncoder encoder;

    Item item;
    Item item2;

    @BeforeEach
    void setUp() {
        item = Item.builder()
                .name("testItem")
                .type(Types.ACCESSORIES)
                .quantity(100)
                .description("testDescription")
                .image(null)
                .price(49.99)
                .build();
        item2 = Item.builder()
                .name("testItem2")
                .type(Types.SMARTHOME)
                .quantity(439)
                .description("testDescription2")
                .image(null)
                .price(110.99)
                .build();

        itemRepository.save(item);
        itemRepository.save(item2);
    }

    @Nested
    @DisplayName("GET Item Test")
    class getItems {

        @Test
        void shouldReturnAllItems() {
            String expected = List.of(
                    new ResponseItem(
                            item.getName(),
                            item.getType(),
                            item.getQuantity(),
                            item.getDescription(),
                            item2.getImage(),
                            item.getPrice()
                            ),
                    new ResponseItem(
                            item2.getName(),
                            item2.getType(),
                            item2.getQuantity(),
                            item2.getDescription(),
                            item2.getImage(),
                            item2.getPrice()
                    )
            ).toString();
            String actual = itemService.getAllItems().toString();

            assertEquals(expected, actual);
            assertEquals(2, itemService.getAllItems().size());
        }
    }

    @AfterEach
    @Transactional
    void tearDown() {
        itemRepository.deleteAll();
    }
}