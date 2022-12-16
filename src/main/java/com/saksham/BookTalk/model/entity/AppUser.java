package com.saksham.BookTalk.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.*;

@Entity @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String userName;
    private String email;
    private String password;
    private String role;
    private LocalDate createdAt = LocalDate.now();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = ALL, fetch = LAZY)
    private Set<Review> reviews;

    @OneToMany(mappedBy = "user", cascade = ALL, fetch = EAGER)
    private Set<BookShelf> bookshelf;

    @JsonIgnore
    @OneToMany(mappedBy = "likedBy", cascade = ALL, fetch = LAZY)
    private Set<Review> likedReviews;
}
