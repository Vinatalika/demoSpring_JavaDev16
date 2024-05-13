package com.example.demo.controller;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.dto.UserDto;
import com.example.demo.service.exception.UserAlreadyExistException;
import com.example.demo.service.exception.UserIncorrectPasswordException;
import com.example.demo.service.exception.UserNotFoundException;
import com.example.demo.service.request.CreateUserRequest;
import com.example.demo.service.request.UpdateUserRequest;
import com.example.demo.service.response.UserResponse;
import com.example.demo.service.userservice.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@Validated
@Controller
@RequestMapping("/note/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/create")
    public ResponseEntity<UserResponse> registrationUser(@Valid @NotNull @RequestBody CreateUserRequest request)
            throws UserAlreadyExistException {
        UserDto newUser = userService.registrationUser(request.getUsername(), request.getPassword());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userMapper.toUserResponse(newUser));
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(
            @Valid @NotNull @RequestBody CreateUserRequest request, HttpServletResponse response) throws UserNotFoundException, UserIncorrectPasswordException {
        UserDto user = userService.login(request.getUsername(), request.getPassword());
        Cookie cookie = new Cookie("user_id", String.valueOf(user.getId()));
        cookie.setPath("/note/");
        response.addCookie(cookie);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(userMapper.toUserResponse(user));
    }

    @GetMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpServletResponse response) {
        response.addCookie(new Cookie("user_id", null));
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateUser(
            @CookieValue(value = "user_id") Long user_id,
            @Valid @NotNull @RequestBody UpdateUserRequest request)
            throws UserNotFoundException, UserIncorrectPasswordException {
        UserDto newUser = userService.updateUser(user_id, request.getOldUsername(), request.getOldPassword(),
                request.getNewUsername(), request.getNewPassword());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userMapper.toUserResponse(newUser));
    }
}
