package com.saksham.BookTalk.controller;

import com.saksham.BookTalk.model.dto.*;
import com.saksham.BookTalk.service.AppUserService;
import com.saksham.BookTalk.service.BookShelfService;
import com.saksham.BookTalk.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @AllArgsConstructor @RequestMapping(path = "/api")
public class AppUserController {
    private final AppUserService appUserService;
    private BookShelfService bookShelfService;
    private ReviewService reviewService;

    @GetMapping("/user")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getUsers() {
        ApiResponse<List<UserResponse>> response = new ApiResponse<>(true, appUserService.getAllUsers());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable("id") Long id) {
        ApiResponse<UserResponse> response = new ApiResponse<>(true, appUserService.getUserById(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{id}/bookshelf")
    ResponseEntity<ApiResponse<List<BookShelfResponse>>> getAllBookShelvesByUser(@PathVariable("id") Long id) {
        ApiResponse<List<BookShelfResponse>> response =
                new ApiResponse<>(true, bookShelfService.getAllBookShelves(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{id}/review")
    ResponseEntity<ApiResponse<List<ReviewResponse>>> getAllReviewsByUser(@PathVariable("id") Long id) {
        ApiResponse<List<ReviewResponse>> response = new ApiResponse<>(true, reviewService.getReviewsByUser(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{id}/liked-reviews")
    ResponseEntity<ApiResponse<List<ReviewResponse>>> getReviewsLikedByUser(@PathVariable("id") Long id) {
        ApiResponse<List<ReviewResponse>> response =
                new ApiResponse<>(true, reviewService.getReviewsLikedByUser(id));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user/{userName}/exists")
    ResponseEntity<ApiResponse<Boolean>> isUserNameUnique(@PathVariable String userName) {
        return ResponseEntity.ok(new ApiResponse<>(
                true, appUserService.isUserNameUnique(userName))
        );
    }

}
