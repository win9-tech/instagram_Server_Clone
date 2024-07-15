package com.example.instagramclone.domain.post.repository;

import com.example.instagramclone.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p.id FROM Post p WHERE p.user.id = :userId")
    List<Long> findIdsByUserId(@Param("userId") Long userId);
}