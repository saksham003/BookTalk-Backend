package com.saksham.BookTalk.controller;

import com.saksham.BookTalk.model.dto.ApiResponse;
import com.saksham.BookTalk.model.dto.LogInRequest;
import com.saksham.BookTalk.model.dto.SignUpRequest;
import com.saksham.BookTalk.model.dto.TokenResponse;
import com.saksham.BookTalk.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller @AllArgsConstructor @RequestMapping(path = "/api/auth")
public class AuthController {
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponse>> LogIn(@RequestBody LogInRequest signInRequest) throws Exception {
        ApiResponse<TokenResponse> response = new ApiResponse<>(true, authService.logIn(signInRequest));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<TokenResponse>> signup(@RequestBody SignUpRequest signUpRequest) {
        ApiResponse<TokenResponse> response = new ApiResponse<>(true, authService.register(signUpRequest));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<ApiResponse<String>> test() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Api is working..."));
    }
}
