package com.example.instagramclone.domain.post.service;

import com.example.instagramclone.auth.util.JwtUtil;
import com.example.instagramclone.domain.image.entity.Image;
import com.example.instagramclone.domain.image.service.ImageService;
import com.example.instagramclone.domain.post.dto.request.CreatePostRequestDto;
import com.example.instagramclone.domain.post.dto.request.DeletePostRequestDto;
import com.example.instagramclone.domain.post.entity.Post;
import com.example.instagramclone.domain.post.repository.PostRepository;
import com.example.instagramclone.domain.user.entity.User;
import com.example.instagramclone.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ImageService imageService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public void createPost(CreatePostRequestDto createPostRequestDto, List<MultipartFile> imageList, String token) {

        Long id = jwtUtil.getUserId(token);
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        Post post = createPostRequestDto.toEntity();
        post.setUser(user);
        postRepository.save(post);

        imageService.uploadAndCreateImages(imageList, post);
    }

    @Override
    public void deletePost(DeletePostRequestDto deletePostRequestDto, String token) {
        Long userId = jwtUtil.getUserId(token);
        userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Post post = postRepository.findById(deletePostRequestDto.getPostId()).orElseThrow(() -> new EntityNotFoundException("Post not found"));

        if (!Objects.equals(post.getUser().getId(), userId)) {
            return;
        }
        imageService.deleteImage(post.getId());
        postRepository.delete(post);
    }
}