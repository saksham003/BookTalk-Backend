package com.saksham.BookTalk.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;

@Entity @AllArgsConstructor @NoArgsConstructor
@Getter
@Setter
@ToString
public class Review {
    @Id @GeneratedValue(strategy = AUTO)
    private Long Id;

    @Column(length = 1000)
    private String body;

    private Integer rating;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private AppUser user;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "book_id")
    private Book book;

    private Date createdAt = new Date();
    private Boolean isEdited = false;

    @ManyToMany(fetch = EAGER)
    @JoinTable(
            name = "reviews_user_likes",
            joinColumns = @JoinColumn(name = "review_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private Set<AppUser> likedBy;

    public void addLike(AppUser user) {
        likedBy.add(user);
    }

    public void removeLike(AppUser user) {
        likedBy.remove(user);
    }
}
