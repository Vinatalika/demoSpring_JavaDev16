package com.example.demo.service.response;


import com.example.demo.service.dto.NoteDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String password;
    private String role;
    private Boolean enabled;
    private List<NoteDto> notes;

    public void setNotes(List<NoteDto> notes) {
        this.notes = notes;
    }
}