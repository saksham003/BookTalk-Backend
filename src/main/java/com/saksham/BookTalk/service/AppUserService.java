package com.saksham.BookTalk.service;

import com.saksham.BookTalk.model.dto.UserResponse;
import com.saksham.BookTalk.model.entity.AppUser;

import java.util.List;

public interface AppUserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    boolean isUserNameUnique(String userName);
}
