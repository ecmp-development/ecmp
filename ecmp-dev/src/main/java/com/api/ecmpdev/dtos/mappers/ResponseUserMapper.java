package com.api.ecmpdev.dtos.mappers;

import com.api.ecmpdev.dtos.ResponseUser;
import com.api.ecmpdev.models.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ResponseUserMapper implements Function<User, ResponseUser> {

    @Override
    public ResponseUser apply(User user) {
        return new ResponseUser(
                user.getName(),
                user.getFirstname(),
                user.getEmail(),
                user.getRole()
        );
    }
}
