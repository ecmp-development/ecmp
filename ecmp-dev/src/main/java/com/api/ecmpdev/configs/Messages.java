package com.api.ecmpdev.configs;

public class Messages {

    public static String idNotFound(Long id) {
        return "User with ID does not exist. ID: " + id;
    }

    public static String emailNotFound(String email) {
        return "User with email does not exist. Email: " + email;
    }

}
