package com.saksham.BookTalk.service.impl;

import com.saksham.BookTalk.exceptions.ResourceNotFoundException;
import com.saksham.BookTalk.model.dto.BookResponse;
import com.saksham.BookTalk.model.entity.Book;
import com.saksham.BookTalk.repository.BookRepository;
import com.saksham.BookTalk.service.BookService;
import com.saksham.BookTalk.utility.helpers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Override
    public List<BookResponse> getAllBooks() {
        List<Book> books =  bookRepository.findAll();
        return books.stream().map(helpers::convertToBookResponse).toList();
    }

    @Override
    public BookResponse getBookById(String id) {
        Book book =  bookRepository.findByGoogleBooksId(id).orElseThrow(() -> new ResourceNotFoundException("Book", id.toString()));
        return helpers.convertToBookResponse(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
