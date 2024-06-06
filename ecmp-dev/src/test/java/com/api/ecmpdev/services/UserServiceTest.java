package com.api.ecmpdev.services;

import com.api.ecmpdev.AbstractContainerBaseTest;
import com.api.ecmpdev.configs.exceptions.UserEmailNotFoundException;
import com.api.ecmpdev.configs.exceptions.UserIdNotFoundException;
import com.api.ecmpdev.dtos.RequestChangeEmail;
import com.api.ecmpdev.dtos.RequestChangePassword;
import com.api.ecmpdev.dtos.RequestCreateUser;
import com.api.ecmpdev.dtos.ResponseUser;
import com.api.ecmpdev.dtos.auth.RequestAuthEmail;
import com.api.ecmpdev.dtos.auth.RequestAuthId;
import com.api.ecmpdev.dtos.mappers.ResponseUserMapper;
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
import org.springframework.http.ResponseEntity;
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
    ResponseUserMapper responseUserMapper;
    @Autowired
    PasswordEncoder encoder;

    User testUser;
    User testUser2;

    @BeforeEach
    void setUp() {
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
                .order(List.of(Order.builder()
                        .item(List.of(item1))
                        .user(testUser)
                        .date(new Date())
                        .build()))
                .build();

        testUser2 = User.builder()
                .name("testAdmin")
                .firstname("firstnameTest2")
                .email("adminTest@mail.test")
                .password(encoder.encode("adminadmin"))
                .role(Roles.ADMIN)
                .order(null)
                .build();

        userRepository.save(testUser);
        userRepository.save(testUser2);
    }

    @Nested
    @DisplayName("GET User Test")
    class getUsers {

        @Test
        void shouldReturnAllUsersInDatabase() {
            String expected = List.of(
                    new ResponseUser(
                            testUser.getName(),
                            testUser.getFirstname(),
                            testUser.getEmail(),
                            testUser.getRole()),
                    new ResponseUser(
                            testUser2.getName(),
                            testUser2.getFirstname(),
                            testUser2.getEmail(),
                            testUser2.getRole())
            ).toString();
            String actual = userService.getAllUsers().toString();

            assertEquals(expected, actual);
            assertEquals(2, userService.getAllUsers().size());
        }

        @Test
        void shouldReturnUserWithSpecifiedId() {
            String expected = Optional.of(new ResponseUser(
                    testUser2.getName(),
                    testUser2.getFirstname(),
                    testUser2.getEmail(),
                    testUser2.getRole())
            ).toString();


            String actual = userService.getUserById(testUser2.getId()).toString();

            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnErrorMessageWhenNotFoundWithId() {
            long id = 3L;
            Exception exception = assertThrows(UserIdNotFoundException.class, () -> userService.getUserById(id));
            assertEquals("User with ID does not exist. ID: " + id, exception.getMessage());
        }

        @Test
        void shouldReturnTestUserWithSpecifiedEmail() {
            String expected = new ResponseUser(
                    testUser.getName(),
                    testUser.getFirstname(),
                    testUser.getEmail(),
                    testUser.getRole()
            ).toString();

            String actual = userService.getUserByEmail(testUser.getEmail()).toString();

            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnErrorMessageWhenNotFoundWithEmail() {
            String email = "testEmail123@mail.test";
            Exception exception = assertThrows(UserEmailNotFoundException.class, () -> userService.getUserByEmail(email));
            assertEquals("User with email does not exist. Email: " + email, exception.getMessage());
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

            ResponseEntity<String> expectedStatus = new ResponseEntity<>("Created user", HttpStatus.CREATED);
            ResponseEntity<String> actualStatus = userService.createNewUser(createUser);

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

            ResponseEntity<String> expectedStatus = new ResponseEntity<>("Invalid values", HttpStatus.BAD_REQUEST);
            ResponseEntity<String> actualStatus = userService.createNewUser(createUser);

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

            ResponseEntity<String> expectedStatus = new ResponseEntity<>("User already exists with email: " + createUser.getEmail(), HttpStatus.CONFLICT);
            ResponseEntity<String> actualStatus = userService.createNewUser(createUser);

            boolean expected = encoder.matches(createUser.getPassword(), userRepository.findByEmail(createUser.getEmail()).orElseThrow().getPassword());

            assertEquals(expectedStatus, actualStatus);
            assertFalse(expected);
        }

    }

    @Nested
    @DisplayName("PUT User Test")
    class changeUser {

        @Test
        void shouldChangePasswordOfUserSpecifiedId() {
            RequestChangePassword testData = RequestChangePassword.builder()
                    .email("customerTest@mail.test")
                    .oldPassword("123")
                    .newPassword("321")
                    .build();

            ResponseEntity<String> expectedStatus = new ResponseEntity<>("Changed password for user: " + testData.getEmail(), HttpStatus.OK);
            ResponseEntity<String> actualStatus = userService.changePassword(testData);

            boolean expected = encoder.matches("321", userRepository.findByEmail(testData.getEmail()).orElseThrow().getPassword());

            assertEquals(expectedStatus, actualStatus);
            assertTrue(expected);
        }

        @Test
        void shouldReturnUnauthorizedWhenPasswordIsWrongForPasswordChange() {
            RequestChangePassword testData = RequestChangePassword.builder()
                    .email("customerTest@mail.test")
                    .oldPassword("111")
                    .newPassword("321")
                    .build();

            ResponseEntity<String> expectedStatus = new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
            ResponseEntity<String> actualStatus = userService.changePassword(testData);

            boolean expected = encoder.matches("321", userRepository.findByEmail(testData.getEmail()).orElseThrow().getPassword());

            assertEquals(expectedStatus, actualStatus);
            assertFalse(expected);
        }

        @Test
        void shouldReturnExceptionWhenUserNotFoundForPasswordChange() {
            RequestChangePassword testData = RequestChangePassword.builder()
                    .email("testEmail22@mail.test")
                    .oldPassword("111")
                    .newPassword("321")
                    .build();

            Exception exception = assertThrows(UserEmailNotFoundException.class, () -> userService.changePassword(testData));

            assertEquals("User with email does not exist. Email: " + testData.getEmail(), exception.getMessage());
        }

        @Test
        void shouldChangeEmailOfUserSpecifiedId() {
            RequestChangeEmail testData = RequestChangeEmail.builder()
                    .oldEmail("customerTest@mail.test")
                    .newEmail("customerTest123@mail.test")
                    .password("123")
                    .build();

            ResponseEntity<String> expectedStatus = new ResponseEntity<>("Changed email for user: " + testData.getOldEmail() + " (Now " + testData.getNewEmail() + ")", HttpStatus.OK);
            ResponseEntity<String> actualStatus = userService.changeEmail(testData);

            boolean expected = userRepository.findByEmail(testData.getNewEmail()).isPresent();

            assertEquals(expectedStatus, actualStatus);
            assertTrue(expected);
        }

        @Test
        void shouldReturnUnauthorizedWhenPasswordIsWrongForEmailChange() {
            RequestChangeEmail testData = RequestChangeEmail.builder()
                    .oldEmail("customerTest@mail.test")
                    .newEmail("customerTest123@mail.test")
                    .password("111")
                    .build();

            ResponseEntity<String> expectedStatus = new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
            ResponseEntity<String> actualStatus = userService.changeEmail(testData);

            boolean expected = userRepository.findByEmail(testData.getNewEmail()).isPresent();

            assertEquals(expectedStatus, actualStatus);
            assertFalse(expected);
        }

        @Test
        void shouldReturnExceptionWhenUserNotFoundForEmailChange() {
            RequestChangeEmail testData = RequestChangeEmail.builder()
                    .oldEmail("testEmail22@mail.test")
                    .newEmail("testEmail123@mail.test")
                    .password("111")
                    .build();

            Exception exception = assertThrows(UserEmailNotFoundException.class, () -> userService.changeEmail(testData));

            assertEquals("User with email does not exist. Email: " + testData.getOldEmail(), exception.getMessage());
        }

    }

    @Nested
    @DisplayName("DELETE User Test")
    class deleteUser {

        @Test
        void shouldDeleteUserSpecifiedId() {
            RequestAuthId testData = new RequestAuthId(testUser.getId(), "123");

            ResponseEntity<String> expected = new ResponseEntity<>("Deleted user with id: " + testData.getId(), HttpStatus.OK);
            ResponseEntity<String> actual = userService.removeUserById(testData);

            assertEquals(expected, actual);
            assertTrue(userRepository.findById(testUser.getId()).isEmpty());

        }

        @Test
        void shouldReturnUnauthorizedWhenPasswordIsWrongForDeleteWithId() {
            RequestAuthId testData = new RequestAuthId(testUser.getId(), "222");

            ResponseEntity<String> expectedStatus = new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
            ResponseEntity<String> actualStatus = userService.removeUserById(testData);

            boolean expected = userRepository.existsById(testData.getId());

            assertEquals(expectedStatus, actualStatus);
            assertTrue(expected);
        }

        @Test
        void shouldReturnErrorMessageWhenNotFoundWithIdAtRemoval() {
            RequestAuthId testData = new RequestAuthId(3L, "123");

            Exception exception = assertThrows(UserIdNotFoundException.class, () -> userService.removeUserById(testData));
            assertEquals("User with ID does not exist. ID: " + testData.getId(), exception.getMessage());
        }

        @Test
        void shouldDeleteUserSpecifiedEmail() {
            RequestAuthEmail testData = new RequestAuthEmail(testUser.getEmail(), "123");

            ResponseEntity<String> expected = new ResponseEntity<>("Deleted user with email: " + testData.getEmail(), HttpStatus.OK);
            ResponseEntity<String> actual = userService.removeUserByEmail(testData);

            assertEquals(expected, actual);
            assertTrue(userRepository.findById(testUser.getId()).isEmpty());

        }

        @Test
        void shouldReturnUnauthorizedWhenPasswordIsWrongForDeleteWithEmail() {
            RequestAuthEmail testData = new RequestAuthEmail(testUser.getEmail(), "222");

            ResponseEntity<String> expectedStatus = new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
            ResponseEntity<String> actualStatus = userService.removeUserByEmail(testData);

            assertEquals(expectedStatus, actualStatus);
            assertTrue(userRepository.existsByEmail(testData.getEmail()));
        }

        @Test
        void shouldReturnErrorMessageWhenNotFoundWithEmailAtRemoval() {
            RequestAuthEmail testData = new RequestAuthEmail("testingDeletion@mail.test", "123");

            Exception exception = assertThrows(UserEmailNotFoundException.class, () -> userService.removeUserByEmail(testData));
            assertEquals("User with email does not exist. Email: " + testData.getEmail(), exception.getMessage());
        }

    }

    @AfterEach
    @Transactional
    void tearDown() {
        userRepository.deleteAll();
    }
}