package com.api.ecmpdev.dtos.mappers;

import com.api.ecmpdev.dtos.ResponseUser;
import com.api.ecmpdev.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ResponseUserMapper implements Function<User, ResponseUser> {

    @Override
    public ResponseUser apply(User user) {
        return new ResponseUser(
                user.getName(),
                user.getFirstname(),
                user.getEmail(),
                user.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
        );
    }
}
