package com.example.instagramclone.auth.dto;

import com.example.instagramclone.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomUserInfoDto{

    private final Long userId;
    private final String email;
    private final String fullName;
    private final String username;
    private final String password;

    public static CustomUserInfoDto from(User user) {
        return CustomUserInfoDto.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .build();
    }
}
