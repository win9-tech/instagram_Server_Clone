package com.example.instagramclone.auth.controller;

import com.example.instagramclone.auth.dto.LoginRequestDto;
import com.example.instagramclone.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<?> authenticateUser(@RequestBody final LoginRequestDto request) throws UsernameNotFoundException, BadCredentialsException {
        String token = authService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}