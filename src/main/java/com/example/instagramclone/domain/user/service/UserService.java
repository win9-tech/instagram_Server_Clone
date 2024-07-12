package com.example.instagramclone.domain.user.service;

import com.example.instagramclone.domain.user.dto.request.CreateUserRequestDto;

public interface UserService {

    void createUser(CreateUserRequestDto createUserRequestDto) throws IllegalAccessException;
}
