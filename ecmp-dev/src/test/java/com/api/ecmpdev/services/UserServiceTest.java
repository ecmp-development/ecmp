package com.api.ecmpdev.services;

import com.api.ecmpdev.enums.Roles;
import com.api.ecmpdev.models.User;
import com.api.ecmpdev.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@RequiredArgsConstructor
class UserServiceTest {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @BeforeEach
    public void createTestUserData() {

        userRepository.save(
                User.builder()
                        .name("testCustomer")
                        .firstname("firstnameTest")
                        .email("customerTest@mail.test")
                        .password(encoder.encode("123"))
                        .role(Roles.CUSTOMER)
                        .order(null)
                        .build()
        );

        userRepository.save(
                User.builder()
                        .name("testAdmin")
                        .firstname("firstnameTest")
                        .email("adminTest@mail.test")
                        .password(encoder.encode("123"))
                        .role(Roles.ADMIN)
                        .order(null)
                        .build()
        );
    }
}