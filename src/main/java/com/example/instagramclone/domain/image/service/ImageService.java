package com.example.instagramclone.domain.image.service;

import com.example.instagramclone.domain.post.entity.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    void uploadAndCreateImages(List<MultipartFile> imageList, Post post);
}
