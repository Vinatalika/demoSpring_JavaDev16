package com.example.demo.service.noteservice;

import com.example.demo.data.entity.NoteEntity;
import com.example.demo.data.repository.NoteNewRepositoryJpa;
import com.example.demo.mapper.NoteMapper;
import com.example.demo.service.dto.NoteDto;
import com.example.demo.service.exception.NoteNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class NoteServiceImp implements NoteService{
    @Autowired private NoteNewRepositoryJpa noteRepository;
    @Autowired private NoteMapper noteMapper;

    @Override
    public List<NoteDto> listAll() {
        return noteMapper.toNoteDtos(noteRepository.findAll());
    }

    @Override
    public NoteDto add(NoteDto note) {
        NoteEntity entity = noteMapper.toNoteEntity(note);
        entity.setId(null);
        return noteMapper.toNoteDto(noteRepository.save(entity));
    }

    @Override
    public void deleteById(UUID id) throws NoteNotFoundException {
        if (!noteRepository.existsById(id)) {
            throw new NoteNotFoundException(id);
        }
        noteRepository.deleteById(id);
    }

    @Override
    public void update(NoteDto note) throws NoteNotFoundException {
        if (Objects.isNull(note.getId()) || !noteRepository.existsById(note.getId())) {
            throw new NoteNotFoundException();
        }
        noteRepository.save(noteMapper.toNoteEntity(note));
    }

    @Override
    public NoteDto getById(UUID id) throws NoteNotFoundException {
        return noteRepository.findById(id)
                .map(noteMapper::toNoteDto)
                .orElseThrow(() -> new NoteNotFoundException(id));
    }
}
