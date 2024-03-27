package com.api.ecmpdev.services;

import com.api.ecmpdev.dtos.RequestAuthId;
import com.api.ecmpdev.dtos.RequestChangePassword;
import com.api.ecmpdev.dtos.RequestCreateUser;
import com.api.ecmpdev.dtos.ResponseUser;
import com.api.ecmpdev.models.User;
import com.api.ecmpdev.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserService(
            UserRepository userRepository,
            PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<ResponseUser> getUserById(Long id) {
        Optional<User> user = Optional.of(userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id not found. ID: " + id)));
        return user.map(value ->
                ResponseUser.builder()
                        .name(value.getName())
                        .firstname(value.getFirstname())
                        .email(value.getEmail())
                        .role(value.getRole())
                .build());
    }

    public Optional<ResponseUser> getUserByEmail(String email) {
        Optional<User> user = Optional.of(userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email not found. Email: " + email)));
        return user.map(value ->
                ResponseUser.builder()
                        .name(value.getName())
                        .firstname(value.getFirstname())
                        .email(value.getEmail())
                        .role(value.getRole())
                .build());
    }

    public HttpStatusCode createNewUser(RequestCreateUser user) {
        if (
                !user.getName().isEmpty()
                        && !user.getFirstname().isEmpty()
                        && !user.getEmail().isEmpty()
                        && !user.getPassword().isEmpty()
                        && !user.getRole().name().isEmpty()
        ) {

            if (!userRepository.existsByEmail(user.getEmail())) {

                userRepository.save(
                        User.builder()
                                .name(user.getName())
                                .firstname(user.getFirstname())
                                .email(user.getEmail())
                                .password(encoder.encode(user.getPassword()))
                                .role(user.getRole())
                                .build()
                );
                return HttpStatus.CREATED;

            } else return HttpStatus.CONFLICT;

        } else return HttpStatus.BAD_REQUEST;
    }

    @Transactional
    public HttpStatusCode changePassword(RequestChangePassword changedPassword) {

        User user = userRepository.findByEmail(changedPassword.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("Email does not exist. Email: " + changedPassword.getEmail()));

        if (encoder.matches(changedPassword.getOldPassword(), user.getPassword())) {
            userRepository.insertNewPassword(user.getId(), encoder.encode(changedPassword.getNewPassword()));
            return HttpStatus.ACCEPTED;
        } else return HttpStatus.UNAUTHORIZED;
    }

    public HttpStatusCode removeUser(RequestAuthId userData) {

        User user = userRepository.findById(userData.getId())
                .orElseThrow(() -> new EntityNotFoundException("User with ID does not exist. ID: " + userData.getId()));

        if (encoder.matches(userData.getPassword(), user.getPassword())) {
            userRepository.deleteById(userData.getId());
            return HttpStatus.OK;
        } else return HttpStatus.UNAUTHORIZED;
    }
}
