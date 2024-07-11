package com.example.instagramclone.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String fullName;

    private String username;

    private String password;

    @Builder
    public User(String email, String fullName, String username) {
        this.email = email;
        this.fullName = fullName;
        this.username = username;
    }

    public void setPassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}
