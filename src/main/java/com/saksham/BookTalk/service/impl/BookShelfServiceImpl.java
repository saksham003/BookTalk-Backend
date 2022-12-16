package com.saksham.BookTalk.service.impl;

import com.saksham.BookTalk.exceptions.ResourceNotFoundException;
import com.saksham.BookTalk.model.dto.BookShelfRequest;
import com.saksham.BookTalk.model.dto.BookShelfResponse;
import com.saksham.BookTalk.model.entity.AppUser;
import com.saksham.BookTalk.model.entity.Book;
import com.saksham.BookTalk.model.entity.BookShelf;
import com.saksham.BookTalk.repository.AppUserRepository;
import com.saksham.BookTalk.repository.BookRepository;
import com.saksham.BookTalk.repository.BookShelfRepository;
import com.saksham.BookTalk.service.BookShelfService;
import com.saksham.BookTalk.utility.helpers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @AllArgsConstructor
public class BookShelfServiceImpl implements BookShelfService {
    private BookShelfRepository bookShelfRepository;
    private AppUserRepository userRepository;
    private BookRepository bookRepository;

    @Override
    public List<BookShelfResponse> getAllBookShelves(Long userId) {
        AppUser user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", userId.toString())
        );
        List<BookShelf> bookShelves = bookShelfRepository.findAllByUser(user);
        return bookShelves.stream().map(helpers::convertToBookShelfResponse).toList();
    }

    @Override
    public BookShelfResponse getBookShelfById(Long bookShelfId) {
        BookShelf bookShelf = bookShelfRepository.findById(bookShelfId).orElseThrow(
                () -> new ResourceNotFoundException("BookShelf", bookShelfId.toString())
        );
        return helpers.convertToBookShelfResponse(bookShelf);
    }

    @Override
    public BookShelfResponse addBookToShelf(Long bookShelfId, String bookId) {
        Book book = bookRepository.findByGoogleBooksId(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book", bookId)
        );

        BookShelf bookShelf = bookShelfRepository.findById(bookShelfId).orElseThrow(
                () -> new ResourceNotFoundException("BookShelf", bookShelfId.toString())
        );

        bookShelf.addBookToShelf(book);
        BookShelf savedBook = bookShelfRepository.save(bookShelf);
        return helpers.convertToBookShelfResponse(savedBook);
    }

    @Override
    public BookShelfResponse removeBookFromShelf(Long bookShelfId, String bookId) {
        Book book = bookRepository.findByGoogleBooksId(bookId).orElseThrow(
                () -> new ResourceNotFoundException("Book", bookId)
        );
        BookShelf bookShelf = bookShelfRepository.findById(bookShelfId).orElseThrow(
                () -> new ResourceNotFoundException("BookShelf", bookShelfId.toString())
        );

        bookShelf.removeBookFromShelf(book);
        BookShelf savedBook = bookShelfRepository.save(bookShelf);
        return helpers.convertToBookShelfResponse(savedBook);
    }

    @Override
    public void deleteBookShelf(Long bookShelfId) {
        bookShelfRepository.deleteById(bookShelfId);
    }

    @Override
    public BookShelfResponse addNewShelf(Long userId, BookShelfRequest bookShelfRequest) {
        AppUser user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", userId.toString())
        );

        BookShelf bookShelf = new BookShelf();
        bookShelf.setName(bookShelfRequest.getName());
        bookShelf.setUser(user);
        BookShelf savedBook = bookShelfRepository.save(bookShelf);
        return helpers.convertToBookShelfResponse(savedBook);
    }
}
