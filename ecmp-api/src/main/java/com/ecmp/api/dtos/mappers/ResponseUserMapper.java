package com.ecmp.api.dtos.mappers;

import com.ecmp.api.dtos.ResponseUser;
import com.ecmp.api.models.User;
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
