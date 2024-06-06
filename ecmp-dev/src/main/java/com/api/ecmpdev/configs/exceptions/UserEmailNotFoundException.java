package com.api.ecmpdev.configs.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class UserEmailNotFoundException extends EntityNotFoundException {

    public UserEmailNotFoundException(String email) {
        super("User with email does not exist. Email: " + email);
    }
}
