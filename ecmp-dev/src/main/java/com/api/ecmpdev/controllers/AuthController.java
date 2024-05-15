package com.api.ecmpdev.controllers;

import com.api.ecmpdev.dtos.RequestAuthentication;
import com.api.ecmpdev.dtos.RequestRegister;
import com.api.ecmpdev.dtos.ResponseAuthentication;
import com.api.ecmpdev.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
public class AuthController {


    private final AuthService authService;

    @PostMapping("register")
    public ResponseEntity<ResponseAuthentication> register(
            @RequestBody RequestRegister request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("login")
    public ResponseEntity<ResponseAuthentication> auth(
            @RequestBody RequestAuthentication request
    ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
