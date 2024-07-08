package com.example.instagramclone.service;

import com.example.instagramclone.dto.request.CreateUserRequestDto;
import com.example.instagramclone.entity.User;
import com.example.instagramclone.repository.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserJPARepository userJPARepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(CreateUserRequestDto createUserRequestDto) throws IllegalAccessException {

        if (userJPARepository.existsByUsername(createUserRequestDto.getUsername())) {
            throw new IllegalAccessException("사용할 수 없는 username 입니다.");
        }
        User user = createUserRequestDto.toEntity();
        String encodedPassword = passwordEncoder.encode(createUserRequestDto.getPassword());
        user.setPassword(encodedPassword);
        userJPARepository.save(user);
    }
}