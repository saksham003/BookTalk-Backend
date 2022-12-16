package com.saksham.BookTalk.service.impl;

import com.saksham.BookTalk.exceptions.ResourceNotFoundException;
import com.saksham.BookTalk.model.entity.AppUser;
import com.saksham.BookTalk.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component @AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUserName(username).orElseThrow(
                () -> new ResourceNotFoundException("User", username)
        );
//        Collection<SimpleGrantedAuthority> authority = new ArrayList<>();
//        authority.add(new SimpleGrantedAuthority("USER"));
        return UserDetailsImpl.build(user);
//        return new User(user.getUserName(), user.getPassword(), authority);
    }
}
