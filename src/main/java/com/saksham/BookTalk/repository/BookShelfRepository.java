package com.saksham.BookTalk.repository;

import com.saksham.BookTalk.model.entity.AppUser;
import com.saksham.BookTalk.model.entity.BookShelf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookShelfRepository extends JpaRepository<BookShelf, Long> {
    List<BookShelf> findAllByUser(AppUser user);
}
