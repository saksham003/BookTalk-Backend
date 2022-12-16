package com.saksham.BookTalk.service.impl;

import com.saksham.BookTalk.exceptions.ResourceNotFoundException;
import com.saksham.BookTalk.model.dto.ReviewRequest;
import com.saksham.BookTalk.model.dto.ReviewResponse;
import com.saksham.BookTalk.model.entity.AppUser;
import com.saksham.BookTalk.model.entity.Book;
import com.saksham.BookTalk.model.entity.Review;
import com.saksham.BookTalk.repository.AppUserRepository;
import com.saksham.BookTalk.repository.BookRepository;
import com.saksham.BookTalk.repository.ReviewRepository;
import com.saksham.BookTalk.service.ReviewService;
import com.saksham.BookTalk.utility.helpers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.saksham.BookTalk.utility.helpers.convertToReviewResponse;

@Service @AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    ReviewRepository reviewRepository;
    BookRepository bookRepository;
    AppUserRepository userRepository;

    @Override
    public List<ReviewResponse> getReviewsByUser(Long userId) {
        AppUser user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", userId.toString())
        );
        List<Review> reviews = reviewRepository.findAllByUser(user);
        return reviews.stream().map(helpers::convertToReviewResponse).toList();
    }

    public ReviewResponse getReview(Long id) {
        Review review =  reviewRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Review", id.toString())
        );
        return convertToReviewResponse(review);
    }

    public ReviewResponse addReview(ReviewRequest reviewRes, String bookId, Long userId) {
        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", userId.toString()));
        Optional<Book> bookOptional = bookRepository.findByGoogleBooksId(bookId);
        Book book;
        if (bookOptional.isEmpty()) {
            Book newBook = new Book();
            newBook.setGoogleBooksId(bookId);
            bookRepository.save(newBook);

            book = bookRepository.findByGoogleBooksId(bookId).get();
        } else {
            book = bookOptional.get();
        }

        Review review = new Review();
        review.setBody(reviewRes.getBody());
        review.setRating(reviewRes.getRating());
        review.setBook(book);
        review.setUser(user);

        Review savedReview =  reviewRepository.save(review);
        return helpers.convertToReviewResponse(savedReview);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public ReviewResponse updateReview(ReviewRequest reviewRes, Long id) {
        Review review = reviewRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Review", id.toString())
        );
        if (reviewRes.getRating() != null) {
            review.setRating(reviewRes.getRating());
        }
        if (reviewRes.getBody() != null) {
            review.setBody(reviewRes.getBody());
        }
        review.setIsEdited(true);
        Review savedReview =  reviewRepository.save(review);
        return helpers.convertToReviewResponse(savedReview);
    }

    @Override
    public List<ReviewResponse> getReviewsLikedByUser(Long userId) {
        AppUser user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", userId.toString())
        );

        return reviewRepository.findAllByLikedBy(user)
                .stream().map(helpers::convertToReviewResponse).toList();
    }

    @Override
    public ReviewResponse likeReview(Long id, Long userId) {
        Review review = reviewRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Review", id.toString())
        );
        AppUser user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", userId.toString())
        );

        review.addLike(user);
        Review savedReview = reviewRepository.save(review);
        return helpers.convertToReviewResponse(savedReview);
    }

    @Override
    public ReviewResponse unlikeReview(Long id, Long userId) {
        Review review = reviewRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Review", id.toString())
        );
        AppUser user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", userId.toString())
        );

        review.removeLike(user);
        Review savedReview = reviewRepository.save(review);
        return helpers.convertToReviewResponse(savedReview);
    }


}
