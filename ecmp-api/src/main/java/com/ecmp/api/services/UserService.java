package com.ecmp.api.services;

import com.ecmp.api.configs.exceptions.UserEmailNotFoundException;
import com.ecmp.api.configs.exceptions.UserIdNotFoundException;
import com.ecmp.api.dtos.RequestChangeEmail;
import com.ecmp.api.dtos.RequestChangePassword;
import com.ecmp.api.dtos.ResponseUser;
import com.ecmp.api.dtos.auth.RequestAuthEmail;
import com.ecmp.api.dtos.auth.RequestAuthId;
import com.ecmp.api.dtos.mappers.ResponseUserMapper;
import com.ecmp.api.models.Order;
import com.ecmp.api.models.User;
import com.ecmp.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    public Optional<ResponseUser> getUserById(Long id) {
        return Optional.of(userRepository.findById(id)
                .map(responseUserMapper)
                .orElseThrow(() -> new UserIdNotFoundException(id)));
    }

    public ResponseUser getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(responseUserMapper)
                .orElseThrow(() -> new UserEmailNotFoundException(email));
    }

    public List<Order> getOrdersOfUsers(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserEmailNotFoundException(email))
                .getOrder();
    }

    @Transactional
    public ResponseEntity<String> changeEmail(RequestChangeEmail data) {

        User user = userRepository.findByEmail(data.getOldEmail())
                .orElseThrow(() -> new UserEmailNotFoundException(data.getOldEmail()));

        if(encoder.matches(data.getPassword(), user.getPassword())) {
            userRepository.insertNewEmail(user.getId(), data.getNewEmail());
            return new ResponseEntity<>("Changed email for user: " + data.getOldEmail() + " (Now " + data.getNewEmail() + ")", HttpStatus.OK);

        } else return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
    }

    @Transactional
    public ResponseEntity<String> changePassword(RequestChangePassword data) {

        User user = userRepository.findByEmail(data.getEmail())
                .orElseThrow(() -> new UserEmailNotFoundException(data.getEmail()));

        if (encoder.matches(data.getOldPassword(), user.getPassword())) {
            userRepository.insertNewPassword(user.getId(), encoder.encode(data.getNewPassword()));
            return new ResponseEntity<>("Changed password for user: " + data.getEmail(), HttpStatus.OK);

        } else return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<String> removeUserById(RequestAuthId userData) {

        User user = userRepository.findById(userData.getId())
                .orElseThrow(() -> new UserIdNotFoundException(userData.getId()));

        if (encoder.matches(userData.getPassword(), user.getPassword())) {
            userRepository.delete(user);
            return new ResponseEntity<>("Deleted user with id: " + userData.getId(), HttpStatus.OK);

        } else return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<String> removeUserByEmail(RequestAuthEmail userData) {

        User user = userRepository.findByEmail(userData.getEmail())
                .orElseThrow(() -> new UserEmailNotFoundException(userData.getEmail()));

        if (encoder.matches(userData.getPassword(), user.getPassword())) {
            userRepository.delete(user);
            return new ResponseEntity<>("Deleted user with email: " + userData.getEmail(), HttpStatus.OK);

        } else return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
    }
}
