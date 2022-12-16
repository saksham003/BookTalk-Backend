package com.saksham.BookTalk.service;

import com.saksham.BookTalk.model.dto.ReviewRequest;
import com.saksham.BookTalk.model.dto.ReviewResponse;
import com.saksham.BookTalk.model.entity.Review;

import java.util.List;

public interface ReviewService {
    List<ReviewResponse> getReviewsByUser(Long userId);
    ReviewResponse getReview(Long id);
    ReviewResponse addReview(ReviewRequest reviewRes, String bookId, Long userId);
    void deleteReview(Long id);
    ReviewResponse updateReview(ReviewRequest reviewRes, Long id);
    List<ReviewResponse> getReviewsLikedByUser(Long userId);
    ReviewResponse likeReview(Long id, Long userId);
    ReviewResponse unlikeReview(Long id, Long userId);
}
