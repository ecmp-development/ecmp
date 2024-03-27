package com.api.ecmpdev.services;

import com.api.ecmpdev.AbstractContainerBaseTest;
import com.api.ecmpdev.dtos.RequestAuthId;
import com.api.ecmpdev.dtos.RequestChangePassword;
import com.api.ecmpdev.dtos.RequestCreateUser;
import com.api.ecmpdev.dtos.ResponseUser;
import com.api.ecmpdev.enums.Roles;
import com.api.ecmpdev.enums.Types;
import com.api.ecmpdev.models.Item;
import com.api.ecmpdev.models.Order;
import com.api.ecmpdev.models.User;
import com.api.ecmpdev.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
        Item item1 = Item.builder()
                .name("testItem")
                .type(Types.ACCESSORIES)
                .quantity(10)
                .description("This is a test item")
                .image(null)
                .price(19.00)
                .build();

        testUser = User.builder()
                .name("testCustomer")
                .firstname("firstnameTest")
                .email("customerTest@mail.test")
                .password(encoder.encode("123"))
                .role(Roles.CUSTOMER)
                .order(null)
                .build();

        testUser2 = User.builder()
                .name("testAdmin")
                .firstname("firstnameTest2")
                .email("adminTest@mail.test")
                .password(encoder.encode("adminadmin"))
                .role(Roles.ADMIN)
                .order(null)
                .build();

        Order order = Order.builder()
                .item(List.of(item1))
                .user(testUser)
                .date(new Date())
                .build();

        order.setItem(List.of(item1));
        testUser.setOrder(List.of(order, order));

        userRepository.save(testUser);
        userRepository.save(testUser2);
    }

    @Nested
    @DisplayName("GET Users Test")
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

    @Nested
    @DisplayName("POST User Test")
    class postUser {

        @Test
        void shouldCreateUserWithGivenData() {
            RequestCreateUser createUser = RequestCreateUser.builder()
                    .name("testUser1")
                    .firstname("test1")
                    .email("test1@mail.test")
                    .password("123123")
                    .role(Roles.CUSTOMER)
                    .build();

            HttpStatusCode expectedStatus = HttpStatus.CREATED;
            HttpStatusCode actualStatus = userService.createNewUser(createUser);

            boolean expected = userRepository.existsByEmail(createUser.getEmail());

            assertEquals(expectedStatus, actualStatus);
            assertTrue(expected);
        }

        @Test
        void shouldReturnBadRequestWhenRequestDataEmpty() {
            RequestCreateUser createUser = RequestCreateUser.builder()
                    .name("")
                    .firstname("test1")
                    .email("test1@mail.test")
                    .password("123123")
                    .role(Roles.CUSTOMER)
                    .build();

            HttpStatusCode expectedStatus = HttpStatus.BAD_REQUEST;
            HttpStatusCode actualStatus = userService.createNewUser(createUser);

            boolean expected = userRepository.existsByEmail(createUser.getEmail());

            assertEquals(expectedStatus, actualStatus);
            assertFalse(expected);
        }

        @Test
        void shouldReturnConflictWhenEmailExists() {
            RequestCreateUser createUser = RequestCreateUser.builder()
                    .name("testUser1")
                    .firstname("test1")
                    .email("customerTest@mail.test")
                    .password("123123")
                    .role(Roles.CUSTOMER)
                    .build();

            HttpStatusCode expectedStatus = HttpStatus.CONFLICT;
            HttpStatusCode actualStatus = userService.createNewUser(createUser);

            boolean expected = encoder.matches(createUser.getPassword(), userRepository.findByEmail(createUser.getEmail()).get().getPassword());

            assertEquals(expectedStatus, actualStatus);
            assertFalse(expected);
        }

    }

    @Nested
    @DisplayName("PUT User-Data Test")
    class changeUser {

        @Test
        void shouldChangePasswordOfUserSpecifiedId() {
            RequestChangePassword testData = RequestChangePassword.builder()
                    .email("customerTest@mail.test")
                    .oldPassword("123")
                    .newPassword("321")
                    .build();

            HttpStatusCode expectedStatus = HttpStatus.ACCEPTED;
            HttpStatusCode actualStatus = userService.changePassword(testData);

            boolean expected = encoder.matches("321", userRepository.findByEmail(testData.getEmail()).get().getPassword());

            assertEquals(expectedStatus, actualStatus);
            assertTrue(expected);
        }

        @Test
        void shouldReturnUnauthorizedWhenPasswordIsWrong() {
            RequestChangePassword testData = RequestChangePassword.builder()
                    .email("customerTest@mail.test")
                    .oldPassword("111")
                    .newPassword("321")
                    .build();

            HttpStatusCode expectedStatus = HttpStatus.UNAUTHORIZED;
            HttpStatusCode actualStatus = userService.changePassword(testData);

            boolean expected = encoder.matches("321", userRepository.findByEmail(testData.getEmail()).get().getPassword());

            assertEquals(expectedStatus, actualStatus);
            assertFalse(expected);
        }

    }

    @Nested
    @DisplayName("DELETE User Test")
    class deleteUser {

        @Test
        void shouldDeleteUserSpecifiedId() {
            RequestAuthId testData = RequestAuthId.builder()
                    .id(testUser.getId())
                    .password("123")
                    .build();

            HttpStatusCode expected = HttpStatus.OK;
            HttpStatusCode actual = userService.removeUser(testData);

            assertEquals(expected, actual);
            assertTrue(userRepository.findById(testUser.getId()).isEmpty());

        }

    }

    @AfterEach
    @Transactional
    void clearTestData() {
        userRepository.deleteAll();
    }
}