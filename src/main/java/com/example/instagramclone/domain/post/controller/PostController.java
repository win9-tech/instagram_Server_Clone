package com.example.instagramclone.domain.post.controller;

import com.example.instagramclone.domain.post.dto.request.CreatePostRequestDto;
import com.example.instagramclone.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(
            @RequestPart("data") CreatePostRequestDto createPostRequestDto,
            @RequestPart("files") List<MultipartFile> imageList,
            @RequestHeader("Authorization") String token){

        postService.createPost(createPostRequestDto, imageList, token);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}