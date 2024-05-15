package com.api.ecmpdev.services;

import com.api.ecmpdev.AbstractContainerBaseTest;
import com.api.ecmpdev.enums.Types;
import com.api.ecmpdev.models.Item;
import com.api.ecmpdev.repositories.ItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

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
                .build();
    }

    @AfterEach
    void tearDown() {
    }
}