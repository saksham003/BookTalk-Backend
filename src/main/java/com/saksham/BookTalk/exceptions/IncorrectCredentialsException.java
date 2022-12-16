package com.saksham.BookTalk.exceptions;

public class IncorrectCredentialsException extends RuntimeException {

    public IncorrectCredentialsException() {
        super("Incorrect username or password");
    }
}
