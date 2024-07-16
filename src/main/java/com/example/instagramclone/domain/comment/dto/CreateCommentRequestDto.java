package com.example.instagramclone.domain.comment.dto;

import com.example.instagramclone.domain.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CreateCommentRequestDto {

    private String text;

    public Comment toEntity(){
        return Comment.builder()
                .text(text)
                .build();
    }
}