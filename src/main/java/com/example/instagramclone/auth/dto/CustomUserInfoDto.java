package com.example.instagramclone.auth.dto;

import com.example.instagramclone.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomUserInfoDto{

    private Long userId;
    private String email;
    private String fullName;
    private String username;
    private String password;

    public static CustomUserInfoDto from(User user) {
        return CustomUserInfoDto.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .build();
    }
}