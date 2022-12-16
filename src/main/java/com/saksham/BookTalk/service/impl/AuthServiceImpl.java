package com.saksham.BookTalk.service.impl;

import com.saksham.BookTalk.exceptions.IncorrectCredentialsException;
import com.saksham.BookTalk.exceptions.ResourceAlreadyExistException;
import com.saksham.BookTalk.model.dto.LogInRequest;
import com.saksham.BookTalk.model.dto.SignUpRequest;
import com.saksham.BookTalk.model.dto.TokenResponse;
import com.saksham.BookTalk.model.entity.AppUser;
import com.saksham.BookTalk.repository.AppUserRepository;
import com.saksham.BookTalk.service.AuthService;
import com.saksham.BookTalk.utility.JwtUtility;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service @AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private AppUserRepository userRepository;
    private JwtUtility jwtUtility;
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public TokenResponse logIn(LogInRequest signInRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            signInRequest.getUserName(),
                            signInRequest.getPassword()
                    )
            );
        } catch(BadCredentialsException ex) {
            throw new IncorrectCredentialsException();
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(signInRequest.getUserName());
        final String token = jwtUtility.generateToken(userDetails);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        return tokenResponse;
    }

    @Override
    public TokenResponse register(SignUpRequest signUpRequest) {
        Optional<AppUser> usernameExists = userRepository.findByUserName(signUpRequest.getUserName());
        if (usernameExists.isPresent()) {
           throw new ResourceAlreadyExistException("User", "username", signUpRequest.getUserName());
        }
        Optional<AppUser> emailExists = userRepository.findByEmail(signUpRequest.getEmail());
        if (emailExists.isPresent()) {
            throw new ResourceAlreadyExistException("User", "Email", signUpRequest.getEmail());
        }

        String currentPassword = signUpRequest.getPassword();
        signUpRequest.setPassword(passwordEncoder.encode(currentPassword));
        userRepository.save(convertToAppUser(signUpRequest));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(signUpRequest.getUserName());
        final String token = jwtUtility.generateToken(userDetails);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        return tokenResponse;
    }

    private AppUser convertToAppUser(SignUpRequest signUpRequest) {
        AppUser user = new AppUser();
        user.setUserName(signUpRequest.getUserName());
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setRole("USER");
        return user;
    }
}
