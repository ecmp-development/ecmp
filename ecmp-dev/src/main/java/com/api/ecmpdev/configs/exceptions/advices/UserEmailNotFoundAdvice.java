package com.api.ecmpdev.configs.exceptions.advices;

import com.api.ecmpdev.configs.exceptions.UserEmailNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserEmailNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(UserEmailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userEmailNotFoundHandler(UserEmailNotFoundException ex) {
        return ex.getMessage();
    }
}
