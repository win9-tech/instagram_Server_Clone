package com.example.instagramclone.user.service;

import com.example.instagramclone.user.dto.request.CreateUserRequestDto;

public interface UserService {

    void createUser(CreateUserRequestDto createUserRequestDto) throws IllegalAccessException;
}
