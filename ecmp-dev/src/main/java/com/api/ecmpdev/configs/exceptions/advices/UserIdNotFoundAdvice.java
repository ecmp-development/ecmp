package com.api.ecmpdev.configs.exceptions.advices;

import com.api.ecmpdev.configs.exceptions.UserIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserIdNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(UserIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userIdNotFoundHandler(UserIdNotFoundException ex) {
        return ex.getMessage();
    }
}
