package com.example.demo.mapper;

import com.example.demo.data.entity.UserEntity;
import com.example.demo.service.dto.UserDto;
import com.example.demo.service.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class UserMapper {

    @Autowired
    private NoteMapper noteMapper;

    public UserDto toUserDto(UserEntity user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        dto.setEnabled(user.getEnabled());
       // dto.setNotes(noteMapper.toNoteDtos(user.getNotes())); // Мапуємо колекцію NoteEntity в колекцію NoteDto
        return dto;
    }

    public UserResponse toUserResponse(UserDto dto) {
        UserResponse response = new UserResponse();
        response.setId(dto.getId());
        response.setUsername(dto.getUsername());
        response.setRole(dto.getRole());
        response.setEnabled(dto.getEnabled());
        response.setNotes(dto.getNotes());
        return response;
    }

    public UserEntity toUserEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        entity.setEnabled(dto.getEnabled());
        // Мапування колекції notes відбувається в сервісному рівні або іншому місці, де це потрібно
        return entity;
    }
}
