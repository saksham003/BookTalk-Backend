package com.saksham.BookTalk.repository;

import com.saksham.BookTalk.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByGoogleBooksId(String googleBooksId);
}
