package com.example.instagramclone.domain.user.controller;

import com.example.instagramclone.domain.user.dto.request.CreateUserRequestDto;
import com.example.instagramclone.domain.user.entity.User;
import com.example.instagramclone.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping
    public ResponseEntity<User> deleteUser() {
        userService.deleteUser();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}