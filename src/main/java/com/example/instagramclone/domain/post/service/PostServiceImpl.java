package com.example.instagramclone.domain.post.service;

import com.example.instagramclone.auth.util.JwtUtil;
import com.example.instagramclone.domain.image.service.ImageService;
import com.example.instagramclone.domain.post.dto.request.CreatePostRequestDto;
import com.example.instagramclone.domain.post.entity.Post;
import com.example.instagramclone.domain.post.repository.PostRepository;
import com.example.instagramclone.domain.user.entity.User;
import com.example.instagramclone.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ImageService imageService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public void createPost(CreatePostRequestDto createPostRequestDto, List<MultipartFile> imageList, String token) {

        Long id = jwtUtil.getUserId(token.substring(7));
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        Post post = createPostRequestDto.toEntity();
        post.setUser(user);
        postRepository.save(post);

        imageService.uploadAndCreateImages(imageList, post);
    }
}