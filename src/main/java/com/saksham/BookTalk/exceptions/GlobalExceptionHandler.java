package com.saksham.BookTalk.exceptions;

import com.saksham.BookTalk.model.dto.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            ResourceNotFoundException.class,
            ResourceAlreadyExistException.class,
            IncorrectCredentialsException.class,
            JwtTokenExpiredException.class,
            ExpiredJwtException.class
    })
    public ResponseEntity<ApiResponse<?>> customExceptionHandler(RuntimeException ex) {
        String message = ex.getMessage();
        ApiResponse<?> response = new ApiResponse<>(false, null, message);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
