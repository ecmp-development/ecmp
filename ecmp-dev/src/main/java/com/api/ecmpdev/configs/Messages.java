package com.api.ecmpdev.configs;

import com.api.ecmpdev.dtos.RequestItem;

public class Messages {

    public static String userIdNotFound(Long id) {
        return "User with ID does not exist. ID: " + id;
    }

    public static String userEmailNotFound(String email) {
        return "User with email does not exist. Email: " + email;
    }

    public static String itemIdNotFound(Long id) {
        return "Item with ID does not exist. ID: " + id;
    }

    public static String itemFilterError(RequestItem payload) {
        return "Item with given Filters not found. Filter: " + payload.toString();
    }
}
