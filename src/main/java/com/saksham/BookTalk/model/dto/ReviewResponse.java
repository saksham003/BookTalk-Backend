package com.saksham.BookTalk.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class ReviewResponse {
    private Long Id;
    private String body;
    private Integer rating;
    private UserResponse user;
    private Date createdAt;
    private Boolean isEdited;
    private Integer likeCount;
}
