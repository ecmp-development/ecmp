package com.api.ecmpdev.services;

import com.api.ecmpdev.dtos.RequestAuthentication;
import com.api.ecmpdev.dtos.RequestRegister;
import com.api.ecmpdev.dtos.ResponseAuthentication;
import com.api.ecmpdev.enums.Roles;
import com.api.ecmpdev.enums.Tokens;
import com.api.ecmpdev.models.Token;
import com.api.ecmpdev.models.User;
import com.api.ecmpdev.repositories.TokenRepository;
import com.api.ecmpdev.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseAuthentication register(RequestRegister request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Roles.CUSTOMER)
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(savedUser, jwtToken);
        return ResponseAuthentication.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(User userModel, String jwtToken) {
        var token = Token.builder()
                .user(userModel)
                .token(jwtToken)
                .type(Tokens.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public ResponseAuthentication authenticate(RequestAuthentication request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return ResponseAuthentication.builder()
                .token(jwtToken)
                .build();
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
