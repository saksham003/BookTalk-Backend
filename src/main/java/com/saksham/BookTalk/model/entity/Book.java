package com.saksham.BookTalk.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id @GeneratedValue(strategy = AUTO)
    private Long id;
    private String googleBooksId;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Review> reviews;
    @JsonIgnore
    @ManyToMany
    private Set<BookShelf> bookShelves;
}
