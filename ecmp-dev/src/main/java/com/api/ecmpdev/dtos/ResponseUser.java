package com.api.ecmpdev.dtos;

import java.util.List;

public record ResponseUser(
        String name,
        String firstname,
        String email,
        List<String> role
) {

}
