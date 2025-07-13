package com.ensep.petshelter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AccountExistException extends RuntimeException {

    public AccountExistException(String message) {
        super(message);
    }
}
