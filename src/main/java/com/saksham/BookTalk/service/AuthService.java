package com.saksham.BookTalk.service;

import com.saksham.BookTalk.model.dto.ApiResponse;
import com.saksham.BookTalk.model.dto.LogInRequest;
import com.saksham.BookTalk.model.dto.SignUpRequest;
import com.saksham.BookTalk.model.dto.TokenResponse;
import com.saksham.BookTalk.model.entity.AppUser;

public interface AuthService {
    public TokenResponse logIn(LogInRequest logInRequest) throws Exception;
    public TokenResponse register(SignUpRequest signUpRequest);
}
