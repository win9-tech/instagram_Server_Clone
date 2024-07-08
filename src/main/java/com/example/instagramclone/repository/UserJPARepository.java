package com.example.instagramclone.repository;

import com.example.instagramclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);
}
