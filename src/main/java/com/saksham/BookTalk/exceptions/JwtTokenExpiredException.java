package com.saksham.BookTalk.exceptions;

public class JwtTokenExpiredException extends RuntimeException{
    public JwtTokenExpiredException (String message) {
        super(message);
    }
}
