package com.saksham.BookTalk.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class LogInRequest {
    public String userName;
    public String password;
}
