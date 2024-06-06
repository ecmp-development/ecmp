package com.api.ecmpdev.dtos;

import com.api.ecmpdev.enums.Roles;

public record ResponseUser(
        String name,
        String firstname,
        String email,
        Roles role
) {

}
