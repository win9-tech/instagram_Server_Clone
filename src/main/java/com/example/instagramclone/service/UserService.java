package com.example.instagramclone.service;

import com.example.instagramclone.dto.request.CreateUserRequestDto;

public interface UserService {

    void createUser(CreateUserRequestDto createUserRequestDto) throws IllegalAccessException;
}
