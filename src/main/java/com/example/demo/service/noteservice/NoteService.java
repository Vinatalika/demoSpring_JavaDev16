package com.example.demo.service.noteservice;

import com.example.demo.service.dto.NoteDto;
import com.example.demo.service.exception.NoteNotFoundException;

import java.util.List;
import java.util.UUID;

public interface NoteService {
    List<NoteDto> listAll();
    NoteDto add(NoteDto note);
    void deleteById(UUID id) throws NoteNotFoundException;
    void update(NoteDto note) throws NoteNotFoundException;
    NoteDto getById(UUID id) throws NoteNotFoundException;
}
