package com.ecmp.api.configs.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ItemIdNotFoundException extends EntityNotFoundException {

    public ItemIdNotFoundException(Long id) {
        super("Item with ID does not exist. ID: " + id);
    }
}
