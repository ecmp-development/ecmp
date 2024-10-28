package com.ecmp.api.configs.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class UserIdNotFoundException extends EntityNotFoundException {

    public UserIdNotFoundException(Long id) {
        super("User with ID does not exist. ID: " + id);
    }
}
