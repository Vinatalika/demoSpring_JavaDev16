package com.example.demo.service.exception;

public class UserAlreadyExistException extends Throwable {

    private static final String USER_ALREADY_EXIST_EXCEPTION_TEXT = "User with username = %s already exist.";

    public UserAlreadyExistException(String username) {
        super(String.format(USER_ALREADY_EXIST_EXCEPTION_TEXT, username));
    }
}
