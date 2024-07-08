package com.example.instagramclone.dto.request;

import com.example.instagramclone.entity.User;
import lombok.Getter;

@Getter
public class CreateUserRequestDto {

    private String username;
    private String password;

    public User toEntity() {
        return User.builder()
                .username(username)
                .build();
    }
}