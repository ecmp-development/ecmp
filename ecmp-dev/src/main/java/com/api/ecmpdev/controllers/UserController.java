package com.api.ecmpdev.controllers;

import com.api.ecmpdev.dtos.RequestChangeEmail;
import com.api.ecmpdev.dtos.auth.RequestAuthEmail;
import com.api.ecmpdev.dtos.auth.RequestAuthId;
import com.api.ecmpdev.dtos.RequestChangePassword;
import com.api.ecmpdev.dtos.RequestCreateUser;
import com.api.ecmpdev.dtos.ResponseUser;
import com.api.ecmpdev.models.Order;
import com.api.ecmpdev.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public List<ResponseUser> getAll() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<ResponseUser> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/email/{email}")
    public ResponseUser getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("/order")
    public List<Order> getUserOrders(@RequestParam String email) {
        return userService.getOrdersOfUsers(email);
    }

    @PutMapping("/update/email")
    public ResponseEntity<String> changeEmail(@RequestBody RequestChangeEmail payload) {
        return userService.changeEmail(payload);
    }

    @PutMapping("/update/password")
    public ResponseEntity<String> changePassword(@RequestBody RequestChangePassword payload) {
        return userService.changePassword(payload);
    }

    @DeleteMapping("/removeId")
    public ResponseEntity<String> removeUserById(@RequestBody RequestAuthId payload) {
        return userService.removeUserById(payload);
    }

    @DeleteMapping("/removeEmail")
    public ResponseEntity<String> removeUserByEmail(@RequestBody RequestAuthEmail payload) {
        return userService.removeUserByEmail(payload);
    }
}
