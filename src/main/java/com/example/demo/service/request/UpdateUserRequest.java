package com.example.demo.service.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {

    @Size(min = 3, max = 45)
    private String oldUsername;

    @Size(min = 3)
    private String oldPassword;

    @Size(min = 3, max = 45)
    private String newUsername;

    @Size(min = 3)
    private String newPassword;
}