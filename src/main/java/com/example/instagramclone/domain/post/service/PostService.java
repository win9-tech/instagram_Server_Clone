package com.example.instagramclone.domain.post.service;

import com.example.instagramclone.domain.post.dto.request.CreatePostRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    void createPost(CreatePostRequestDto createPostRequestDto, List<MultipartFile> imageList, String token);
}