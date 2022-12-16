package com.saksham.BookTalk.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class SignUpRequest {
    public String name;
    public String userName;
    public String email;
    public String password;
}
