package com.saksham.BookTalk.controller;

import com.saksham.BookTalk.model.dto.ApiResponse;
import com.saksham.BookTalk.model.dto.BookResponse;
import com.saksham.BookTalk.model.dto.ReviewRequest;
import com.saksham.BookTalk.model.dto.ReviewResponse;
import com.saksham.BookTalk.service.BookService;
import com.saksham.BookTalk.service.ReviewService;
import com.saksham.BookTalk.service.impl.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @AllArgsConstructor @RequestMapping(path = "/api")
public class BookController {
    private BookService bookService;
    private ReviewService reviewService;

    @GetMapping("/books")
    public ResponseEntity<ApiResponse<List<BookResponse>>> getAllBooks() {
        List<BookResponse> books = bookService.getAllBooks();
        ApiResponse<List<BookResponse>> response = new ApiResponse<>(true, books);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<ApiResponse<BookResponse>> getBookById(@PathVariable("id") String id) {
        ApiResponse<BookResponse> response = new ApiResponse<>(true, bookService.getBookById(id));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<ApiResponse<String>> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        ApiResponse<String> response = new ApiResponse<>(true, "Book Deleted Successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/books/{bookId}/reviews")
    public ResponseEntity<ApiResponse<ReviewResponse>> addReview(
            @PathVariable("bookId") String bookId,
            @RequestBody ReviewRequest review,
            Authentication auth)
    {
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        ApiResponse<ReviewResponse> response = new ApiResponse<>(true, reviewService.addReview(review, bookId, user.getId()));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/books/{bookId}/reviews/{id}")
    public ResponseEntity<ApiResponse<?>> editReview(@PathVariable("id") Long id, @RequestBody ReviewRequest request) {
        ReviewResponse review = reviewService.updateReview(request, id);
        return ResponseEntity.ok(new ApiResponse<>(true, review));
    }

    @DeleteMapping("/books/{bookId}/reviews/{id}")
    public ResponseEntity<ApiResponse<?>> deleteReview(@PathVariable("id") Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Deleted Successfully"));
    }

    @PostMapping("/books/{bookId}/reviews/{id}/likes")
    public ResponseEntity<ApiResponse<ReviewResponse>> likeReview(@PathVariable("id") Long id, Authentication auth) {
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        ApiResponse<ReviewResponse> response = new ApiResponse<>(true, reviewService.likeReview(id, user.getId()));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/books/{bookId}/reviews/{id}/likes")
    public ResponseEntity<ApiResponse<ReviewResponse>> unlikeReview(@PathVariable("id") Long id, Authentication auth) {
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        ApiResponse<ReviewResponse> response = new ApiResponse<>(true, reviewService.unlikeReview(id, user.getId()));
        return ResponseEntity.ok(response);
    }
}