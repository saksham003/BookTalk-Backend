package com.saksham.BookTalk.model.dto;

import com.saksham.BookTalk.model.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class BookResponse {
    private Long id;
    private String googleBooksId;
    private Double rating;
    private List<ReviewResponse> reviews;
}
