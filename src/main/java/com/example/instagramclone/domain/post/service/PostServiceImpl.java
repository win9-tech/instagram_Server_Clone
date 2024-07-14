package com.example.instagramclone.domain.post.service;

import com.example.instagramclone.auth.service.AuthenticationService;
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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final ImageService imageService;
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public void createPost(CreatePostRequestDto createPostRequestDto, List<MultipartFile> imageList) {
        User user = getAuthenticationUser();
        Post post = createPostRequestDto.toEntity();
        post.setUser(user);
        postRepository.save(post);
        imageService.uploadAndCreateImages(imageList, post);
    }

    @Override
    public void deletePost(DeletePostRequestDto deletePostRequestDto) {
        User user = getAuthenticationUser();
        Post post = getPostById(deletePostRequestDto.getPostId());
        verifyPostOwnership(user, post);
        imageService.deleteImage(post.getId());
        postRepository.delete(post);
    }

    private User getAuthenticationUser(){
        Long userId = authenticationService.getAuthenticatedId();
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    private Post getPostById(Long postId){
        return postRepository.findById(postId).orElseThrow(()
                -> new EntityNotFoundException("Post not found"));
    }

    private void verifyPostOwnership(User user, Post post){
        if (!Objects.equals(post.getUser().getId(), user.getId())) {
            throw new AccessDeniedException("You do not have permission to delete this post");
        }
    }
}