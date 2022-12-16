package com.saksham.BookTalk.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity @AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
@ToString
public class BookShelf {
    @Id @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;

    @JsonIgnore
    @ManyToOne
    private AppUser user;

    @ManyToMany(fetch = EAGER)
    @JoinTable(
            name = "bookshelf_book_mapping",
            joinColumns = @JoinColumn(name = "bookshelf_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id")
    )
    private Set<Book> books;

    public void addBookToShelf(Book book) {
        this.books.add(book);
    }

    public void removeBookFromShelf(Book book) {
        this.books.remove(book);
    }
}
