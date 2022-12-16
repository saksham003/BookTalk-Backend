package com.saksham.BookTalk.service.impl;

import com.saksham.BookTalk.exceptions.ResourceNotFoundException;
import com.saksham.BookTalk.model.dto.UserResponse;
import com.saksham.BookTalk.model.entity.AppUser;
import com.saksham.BookTalk.repository.AppUserRepository;
import com.saksham.BookTalk.service.AppUserService;
import com.saksham.BookTalk.utility.helpers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository userRepository;

    @Override
    public List<UserResponse> getAllUsers() {
        List<AppUser> appUsers =  userRepository.findAll();
        List<UserResponse> users = appUsers.stream().map(helpers::convertToUserResponse).toList(); ;
        return users;
    }

    @Override
    public UserResponse getUserById(Long id) {
        AppUser user =  userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", id.toString())
        );
        return helpers.convertToUserResponse(user);
    }

    @Override
    public boolean isUserNameUnique(String userName) {
        return userRepository.existsByUserName(userName);
    }

}
