package com.saksham.BookTalk.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @AllArgsConstructor @NoArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String userName;
    private String email;
    private LocalDate createdAt;
}
