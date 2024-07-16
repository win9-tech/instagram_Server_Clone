package com.example.instagramclone.domain.comment.service;

import com.example.instagramclone.domain.comment.dto.CreateCommentRequestDto;

public interface CommentService {

    void createComment(Long postId, CreateCommentRequestDto createCommentRequestDto);

    void deleteComment(Long postId, Long commentId);
}