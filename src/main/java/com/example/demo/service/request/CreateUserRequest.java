package com.example.demo.service.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

    @Size(min = 3, max = 45)
    private String username;

    @Size(min = 3)
    private String password;

    public CreateUserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}