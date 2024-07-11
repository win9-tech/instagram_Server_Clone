package com.example.instagramclone.user.controller;

import com.example.instagramclone.user.dto.request.CreateUserRequestDto;
import com.example.instagramclone.user.entity.User;
import com.example.instagramclone.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody final CreateUserRequestDto createUserRequestDto) throws IllegalAccessException {
        userService.createUser(createUserRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}