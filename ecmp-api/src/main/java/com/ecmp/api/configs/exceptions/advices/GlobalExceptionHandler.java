package com.ecmp.api.configs.exceptions.advices;

import com.ecmp.api.configs.exceptions.ItemIdNotFoundException;
import com.ecmp.api.configs.exceptions.UserEmailNotFoundException;
import com.ecmp.api.configs.exceptions.UserIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ItemIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String itemIdNotFoundHandler(ItemIdNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userIdNotFoundHandler(UserIdNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(UserEmailNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userEmailNotFoundHandler(UserEmailNotFoundException ex) {
        return ex.getMessage();
    }
}
