package com.ecmp.api.services;

import com.ecmp.api.AbstractContainerBaseTest;
import com.ecmp.api.dtos.mappers.ResponseItemMapper;
import com.ecmp.api.repositories.ItemRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItemServiceTest extends AbstractContainerBaseTest {

    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ResponseItemMapper responseItemMapper;


    @BeforeEach
    void setUp() {

    }

    @Nested
    @DisplayName("GET Item Test")
    class getItems {

        @Test
        void getAllItems() {

        }
    }

    @AfterEach
    void tearDown() throws Exception {
    }
}