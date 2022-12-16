package com.saksham.BookTalk.service;

import com.saksham.BookTalk.model.dto.BookResponse;
import com.saksham.BookTalk.model.entity.Book;

import java.util.List;

public interface BookService {
    List<BookResponse> getAllBooks();
    BookResponse getBookById(String id);
    void deleteBook(Long id);
}
