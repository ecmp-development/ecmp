package com.api.ecmpdev.controllers;

import com.api.ecmpdev.dtos.RequestChangePassword;
import com.api.ecmpdev.dtos.RequestCreateUser;
import com.api.ecmpdev.dtos.ResponseUser;
import com.api.ecmpdev.models.User;
import com.api.ecmpdev.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/")
public class UserController {

    private final UserService userService;

    @GetMapping("all")
    public ResponseEntity<List<User>> getAll() {
        return userService.getAllUsers();
    }

    @PostMapping("create")
    public HttpStatusCode createUser(@RequestBody RequestCreateUser payload) {
        return userService.createNewUser(payload);
    }

    @PutMapping("update/password")
    public HttpStatusCode changePassword(@RequestBody RequestChangePassword payload) {
        return userService.changePassword(payload);
    }

    @GetMapping("email/{email}")
    public Optional<ResponseUser> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @GetMapping("id/{id}")
    public Optional<ResponseUser> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
   }
}
