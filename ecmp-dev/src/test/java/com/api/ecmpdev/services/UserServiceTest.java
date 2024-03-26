package com.api.ecmpdev.services;

import com.api.ecmpdev.AbstractContainerBaseTest;
import com.api.ecmpdev.dtos.ResponseUser;
import com.api.ecmpdev.enums.Roles;
import com.api.ecmpdev.models.User;
import com.api.ecmpdev.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest extends AbstractContainerBaseTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    User testUser;
    User testUser2;

    @BeforeEach
    void createTestData() {
        testUser = User.builder()
                .name("testCustomer")
                .firstname("firstnameTest")
                .email("customerTest@mail.test")
                .password(encoder.encode("123"))
                .role(Roles.CUSTOMER)
                .order(null)
                .build();
        userRepository.save(testUser);

        testUser2 = User.builder()
                .name("testAdmin")
                .firstname("firstnameTest2")
                .email("adminTest@mail.test")
                .password(encoder.encode("adminadmin"))
                .role(Roles.ADMIN)
                .order(null)
                .build();
        userRepository.save(testUser2);
    }

    @Nested
    @DisplayName("get Users Test")
    class getUsers {

        @Test
        void shouldReturnAllUsersInDatabase() {
            String expected = List.of(testUser, testUser2).toString();
            String actual = userService.getAllUsers().toString();

            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnUserWithSpecifiedId() {
            String expected = Optional.of(
                    ResponseUser.builder()
                            .name(testUser2.getName())
                            .firstname(testUser2.getFirstname())
                            .email(testUser2.getEmail())
                            .role(testUser2.getRole())
                            .build()).toString();

            String actual = userService.getUserById(testUser2.getId()).toString();

            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnTestUserWithSpecifiedEmail() {
            String expected = Optional.of(
                    ResponseUser.builder()
                            .name(testUser.getName())
                            .firstname(testUser.getFirstname())
                            .email(testUser.getEmail())
                            .role(testUser.getRole())
                            .build()).toString();

            String actual = userService.getUserByEmail(testUser.getEmail()).toString();

            assertEquals(expected, actual);
        }
    }

    @AfterEach
    @Transactional
    void clearTestData() {
        userRepository.deleteAll();
    }
}