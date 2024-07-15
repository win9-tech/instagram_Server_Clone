package com.example.instagramclone.domain.user.service;

import com.example.instagramclone.auth.service.AuthenticationService;
import com.example.instagramclone.domain.image.service.ImageService;
import com.example.instagramclone.domain.post.entity.Post;
import com.example.instagramclone.domain.post.service.PostService;
import com.example.instagramclone.domain.user.dto.request.CreateUserRequestDto;
import com.example.instagramclone.domain.user.entity.User;
import com.example.instagramclone.domain.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final AuthenticationService authenticationService;
    private final ImageService imageService;
    private final PostService postService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(CreateUserRequestDto createUserRequestDto) throws IllegalAccessException {
        validateEmail(createUserRequestDto.getEmail());
        validateUsername(createUserRequestDto.getUsername());

        User user = createUserRequestDto.toEntity();
        user.setPassword(passwordEncoder.encode(createUserRequestDto.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteUser() {
        User user = getAuthenticationUser();
        List<Long> postIds = postService.getPostIdByUserId(user.getId());
        postIds.forEach(imageService::deleteImage);
        userRepository.delete(user);
    }

    private User getAuthenticationUser(){
        Long userId = authenticationService.getAuthenticatedId();
        return userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    private void validateEmail(String email) throws IllegalAccessException {
        if (userRepository.existsByEmail(email)) {
            throw new IllegalAccessException("사용할 수 없는 email 입니다.");
        }
    }

    private void validateUsername(String username) throws IllegalAccessException {
        if (userRepository.existsByUsername(username)){
            throw new IllegalAccessException("사용할 수 없는 username 입니다.");
        }
    }
}