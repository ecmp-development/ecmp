package com.api.ecmpdev.services;

import com.api.ecmpdev.configs.Messages;
import com.api.ecmpdev.dtos.RequestAuthId;
import com.api.ecmpdev.dtos.RequestChangePassword;
import com.api.ecmpdev.dtos.RequestCreateUser;
import com.api.ecmpdev.dtos.ResponseUser;
import com.api.ecmpdev.dtos.mappers.ResponseUserMapper;
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
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final ResponseUserMapper responseUserMapper;

    @Autowired
    public UserService(
            UserRepository userRepository,
            PasswordEncoder encoder,
            ResponseUserMapper responseUserMapper) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.responseUserMapper = responseUserMapper;
    }

    public List<ResponseUser> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(responseUserMapper)
                .collect(Collectors.toList());
    }

    public ResponseUser getUserById(Long id) {
        return userRepository.findById(id)
                .map(responseUserMapper)
                .orElseThrow(() -> new EntityNotFoundException(
                        Messages.userIdNotFound(id)
                ));
    }

    public ResponseUser getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(responseUserMapper)
                .orElseThrow(() -> new EntityNotFoundException(
                        Messages.userEmailNotFound(email)));
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
                .orElseThrow(() -> new EntityNotFoundException(Messages.userEmailNotFound(changedPassword.getEmail())));

        if (encoder.matches(changedPassword.getOldPassword(), user.getPassword())) {
            userRepository.insertNewPassword(user.getId(), encoder.encode(changedPassword.getNewPassword()));
            return HttpStatus.ACCEPTED;
        } else return HttpStatus.UNAUTHORIZED;
    }

    public HttpStatusCode removeUser(RequestAuthId userData) {

        User user = userRepository.findById(userData.getId())
                .orElseThrow(() -> new EntityNotFoundException(Messages.userIdNotFound(userData.getId())));

        if (encoder.matches(userData.getPassword(), user.getPassword())) {
            userRepository.deleteById(userData.getId());
            return HttpStatus.OK;
        } else return HttpStatus.UNAUTHORIZED;
    }
}
