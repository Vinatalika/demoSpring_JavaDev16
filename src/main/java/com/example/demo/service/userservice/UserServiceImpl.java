package com.example.demo.service.userservice;

import com.example.demo.data.entity.UserEntity;
import com.example.demo.data.repository.UserRepositoryJpa;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.dto.UserDto;
import com.example.demo.service.exception.UserAlreadyExistException;
import com.example.demo.service.exception.UserIncorrectPasswordException;
import com.example.demo.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryJpa userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto registrationUser(String username, String password) throws UserAlreadyExistException {

        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistException("User with username " + username + " already exists");
        }

        UserEntity newUser = new UserEntity();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setRole("USER");
        newUser.setEnabled(true);
        UserEntity savedUser = userRepository.save(newUser);

        return userMapper.toUserDto(savedUser);
    }

    @Override
    public UserDto login(String username, String password) throws UserNotFoundException, UserIncorrectPasswordException {

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found"));

        if (!password.equals(user.getPassword())) {
            throw new UserIncorrectPasswordException("Incorrect password for user " + username);
        }

        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto updateUser(Long userId, String oldUsername, String oldPassword,
                              String newUsername, String newPassword) throws UserNotFoundException, UserIncorrectPasswordException {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

        if (!oldUsername.equals(user.getUsername()) || !oldPassword.equals(user.getPassword())) {
            throw new UserIncorrectPasswordException("Incorrect username or password for user with ID " + userId);
        }

        user.setUsername(newUsername);
        user.setPassword(newPassword);
        UserEntity updatedUser = userRepository.save(user);

        return userMapper.toUserDto(updatedUser);
    }
    @Override
    public UserEntity findById(Long userId) throws UserNotFoundException {

        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
    }

}

