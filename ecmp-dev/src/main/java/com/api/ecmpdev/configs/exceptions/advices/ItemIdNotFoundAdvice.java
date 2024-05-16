package com.api.ecmpdev.configs.exceptions.advices;

import com.api.ecmpdev.configs.exceptions.ItemIdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ItemIdNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ItemIdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String itemIdNotFoundHandler(ItemIdNotFoundException ex) {
        return ex.getMessage();
    }

}
