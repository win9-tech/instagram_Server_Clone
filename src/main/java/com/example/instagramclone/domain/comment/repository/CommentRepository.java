package com.example.instagramclone.domain.comment.repository;

import com.example.instagramclone.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByIdAndPostId(Long commentId, Long postId);
}
