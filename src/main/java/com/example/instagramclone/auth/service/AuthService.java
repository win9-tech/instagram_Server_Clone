package com.example.instagramclone.auth.service;

import com.example.instagramclone.auth.dto.LoginRequestDto;

public interface AuthService {

    String login(LoginRequestDto request);
}