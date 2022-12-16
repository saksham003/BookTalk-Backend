package com.saksham.BookTalk.repository;

import com.saksham.BookTalk.model.entity.AppUser;
import com.saksham.BookTalk.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByUser(AppUser user);
    List<Review> findAllByLikedBy(AppUser user);
}
