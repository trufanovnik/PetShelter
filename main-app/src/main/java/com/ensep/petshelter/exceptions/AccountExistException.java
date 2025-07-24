package com.ensep.petshelter.exceptions;

public class AccountExistException extends RuntimeException {

    public AccountExistException(String message) {
        super(message);
    }
}
