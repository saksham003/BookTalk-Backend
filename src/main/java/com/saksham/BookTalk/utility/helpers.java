package com.saksham.BookTalk.utility;

import com.saksham.BookTalk.model.dto.BookResponse;
import com.saksham.BookTalk.model.dto.BookShelfResponse;
import com.saksham.BookTalk.model.dto.ReviewResponse;
import com.saksham.BookTalk.model.dto.UserResponse;
import com.saksham.BookTalk.model.entity.AppUser;
import com.saksham.BookTalk.model.entity.Book;
import com.saksham.BookTalk.model.entity.BookShelf;
import com.saksham.BookTalk.model.entity.Review;

import java.util.List;

public class helpers {
    public static ReviewResponse convertToReviewResponse(Review review) {
        ReviewResponse response = new ReviewResponse();
        response.setId(review.getId());
        response.setBody(review.getBody());
        response.setRating(review.getRating());
        response.setUser(convertToUserResponse(review.getUser()));
        response.setCreatedAt(review.getCreatedAt());
        response.setIsEdited(review.getIsEdited());
        response.setLikeCount(review.getLikedBy().size());
        return response;
    }

    public static UserResponse convertToUserResponse(AppUser user) {
        UserResponse response = new UserResponse();
        response.setUserName(user.getUserName());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setId(user.getId());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }

    public static BookResponse convertToBookResponse(Book book) {
        BookResponse response = new BookResponse();
        response.setGoogleBooksId(book.getGoogleBooksId());
        response.setId(book.getId());
        List<ReviewResponse> reviews = book.getReviews().stream().map(helpers::convertToReviewResponse).toList();
        response.setReviews(reviews);
        Double rating = book.getReviews().stream().mapToDouble(Review::getRating).average().orElse(0);
        response.setRating(rating);
        return response;
    }

    public static BookShelfResponse convertToBookShelfResponse(BookShelf bookShelf) {
        BookShelfResponse response = new BookShelfResponse();
        response.setId(bookShelf.getId());
        response.setName(bookShelf.getName());
        response.setUserId(bookShelf.getUser().getId());
        List<String> books = bookShelf.getBooks().stream().map(Book::getGoogleBooksId).toList();
        response.setBookIds(books);
        return response;
    }
}
