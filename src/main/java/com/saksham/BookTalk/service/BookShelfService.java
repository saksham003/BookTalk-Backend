package com.saksham.BookTalk.service;

import com.saksham.BookTalk.model.dto.BookShelfRequest;
import com.saksham.BookTalk.model.dto.BookShelfResponse;
import com.saksham.BookTalk.model.entity.BookShelf;

import java.util.List;

public interface BookShelfService {
    List<BookShelfResponse> getAllBookShelves(Long userId);
    BookShelfResponse getBookShelfById(Long bookShelfId);
    BookShelfResponse addBookToShelf(Long bookShelfId, String bookId);
    BookShelfResponse removeBookFromShelf(Long bookShelfId, String bookId);
    void deleteBookShelf(Long bookShelfId);
    BookShelfResponse addNewShelf(Long userId, BookShelfRequest bookShelfRequest);
}
