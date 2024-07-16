package com.example.instagramclone.domain.comment.repository;

import com.example.instagramclone.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
