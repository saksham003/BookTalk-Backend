package com.saksham.BookTalk.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class BookShelfResponse {
    private Long id;
    private String name;
    private Long userId;
    private List<String> bookIds;
}
