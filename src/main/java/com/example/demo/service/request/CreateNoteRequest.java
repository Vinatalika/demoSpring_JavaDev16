package com.example.demo.service.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNoteRequest {
    @Size (min = 3, max = 250)
    private String title;

    @NotBlank
    private String content;

    public CreateNoteRequest (String title, String content) {
        this.title = title;
        this.content = content;
    }
}
