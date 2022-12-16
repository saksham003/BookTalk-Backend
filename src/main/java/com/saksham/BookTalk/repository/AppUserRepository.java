package com.saksham.BookTalk.repository;

import com.saksham.BookTalk.model.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
     Optional<AppUser> findByUserName(String userName);
     Optional<AppUser> findByEmail(String email);

     boolean existsByUserName(String userName);
}
