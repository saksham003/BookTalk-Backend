package com.saksham.BookTalk.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ReviewRequest {
    private String body;
    private Integer rating;
}
