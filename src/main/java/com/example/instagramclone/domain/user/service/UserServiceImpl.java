package com.example.instagramclone.domain.user.service;

import com.example.instagramclone.domain.user.dto.request.CreateUserRequestDto;
import com.example.instagramclone.domain.user.entity.User;
import com.example.instagramclone.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(CreateUserRequestDto createUserRequestDto) throws IllegalAccessException {

        String email = createUserRequestDto.getEmail();
        String username = createUserRequestDto.getUsername();
        String password = createUserRequestDto.getPassword();

        if (userRepository.existsByEmail(email)) {
            throw new IllegalAccessException("사용할 수 없는 email 입니다.");
        }
        if (userRepository.existsByUsername(username)) {
            throw new IllegalAccessException("사용할 수 없는 username 입니다.");
        }
        User user = createUserRequestDto.toEntity();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}