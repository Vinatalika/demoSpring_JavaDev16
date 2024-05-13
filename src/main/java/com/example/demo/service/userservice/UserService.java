package com.example.demo.service.userservice;

import com.example.demo.data.entity.UserEntity;
import com.example.demo.service.dto.UserDto;
import com.example.demo.service.exception.UserAlreadyExistException;
import com.example.demo.service.exception.UserIncorrectPasswordException;
import com.example.demo.service.exception.UserNotFoundException;

public interface UserService {

    UserDto registrationUser(String username, String password) throws UserAlreadyExistException;

    UserDto login(String username, String password) throws UserNotFoundException, UserIncorrectPasswordException;

    UserDto updateUser(Long userId, String oldUsername, String oldPassword,
                       String newUsername, String newPassword) throws UserNotFoundException, UserIncorrectPasswordException;

    UserEntity findById(Long userId) throws UserNotFoundException;
}