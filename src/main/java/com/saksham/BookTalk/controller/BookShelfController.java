package com.saksham.BookTalk.controller;

import com.saksham.BookTalk.model.dto.ApiResponse;
import com.saksham.BookTalk.model.dto.BookShelfRequest;
import com.saksham.BookTalk.model.dto.BookShelfResponse;
import com.saksham.BookTalk.model.entity.BookShelf;
import com.saksham.BookTalk.service.BookShelfService;
import com.saksham.BookTalk.service.impl.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController @AllArgsConstructor @RequestMapping(path = "/api")
public class BookShelfController {
    private BookShelfService bookShelfService;


    @PostMapping("/bookshelf")
    ResponseEntity<ApiResponse<BookShelfResponse>> createBookShelf(@RequestBody BookShelfRequest bookShelfRequest, Authentication auth)
    {
        UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
        ApiResponse<BookShelfResponse> response = new ApiResponse<>(true, bookShelfService.addNewShelf(user.getId(), bookShelfRequest));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/bookshelf/{shelfId}")
    ResponseEntity<ApiResponse<BookShelfResponse>> getBookShelfById(@PathVariable("shelfId") Long shelfId) {
        ApiResponse<BookShelfResponse> response = new ApiResponse<>(true, bookShelfService.getBookShelfById(shelfId));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/bookshelf/{shelfId}")
    ResponseEntity<ApiResponse<String>> deleteBookShelfById(@PathVariable("shelfId") Long shelfId) {
        bookShelfService.deleteBookShelf(shelfId);
        ApiResponse<String > response = new ApiResponse<>(true, "Shelf deleted Successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/bookshelf/{shelfId}/book/{bookId}")
    ResponseEntity<ApiResponse<BookShelfResponse>> addBookToShelf(
            @PathVariable("shelfId") Long shelfId,
            @PathVariable("bookId") String bookId)
    {
        ApiResponse<BookShelfResponse> response = new ApiResponse<>(true, bookShelfService.addBookToShelf(shelfId, bookId));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/bookshelf/{shelfId}/book/{bookId}")
    ResponseEntity<ApiResponse<BookShelfResponse>> removeBookFromShelf(
            @PathVariable("shelfId") Long shelfId,
            @PathVariable("bookId") String bookId)
    {
        ApiResponse<BookShelfResponse> response = new ApiResponse<>(true, bookShelfService.removeBookFromShelf(shelfId, bookId));
        return ResponseEntity.ok(response);
    }

}
