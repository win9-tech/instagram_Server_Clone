package com.example.instagramclone.domain.post.dto.request;

import com.example.instagramclone.domain.post.entity.Post;
import lombok.Getter;

@Getter
public class CreatePostRequestDto {

    private String text;
    private String visibility;

    public Post toEntity(){

        return Post.builder()
                .text(text)
                .visibility(visibility)
                .build();
    }
}