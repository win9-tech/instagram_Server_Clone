package com.example.instagramclone.domain.comment.controller;

import com.example.instagramclone.domain.comment.service.CommentService;
import com.example.instagramclone.domain.comment.dto.CreateCommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<?> createComment(
            @PathVariable Long postId,
            @RequestBody CreateCommentRequestDto createCommentRequestDto) {
        commentService.createComment(postId, createCommentRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}