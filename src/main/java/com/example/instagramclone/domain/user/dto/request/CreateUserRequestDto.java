package com.example.instagramclone.domain.user.dto.request;

import com.example.instagramclone.domain.user.entity.User;
import lombok.Getter;

@Getter
public class CreateUserRequestDto {

    private String email;
    private String fullName;
    private String username;
    private String password;

    public User toEntity() {

        return User.builder()
                .email(email)
                .fullName(fullName)
                .username(username)
                .build();
    }
}