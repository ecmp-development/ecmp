package com.ecmp.api.dtos;

import com.ecmp.api.enums.Roles;

public record ResponseUser(
        String name,
        String firstname,
        String email,
        Roles role
) {

}
