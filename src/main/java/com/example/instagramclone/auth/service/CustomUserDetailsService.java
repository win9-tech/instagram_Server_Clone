package com.example.instagramclone.auth.service;

import com.example.instagramclone.auth.dto.CustomUserInfoDto;
import com.example.instagramclone.auth.model.CustomUserDetails;
import com.example.instagramclone.domain.user.entity.User;
import com.example.instagramclone.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final static String NOT_FOUND_USER = "해당하는 유저가 없습니다.";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND_USER));
        CustomUserInfoDto customUserInfoDto = CustomUserInfoDto.from(user);

        return new CustomUserDetails(customUserInfoDto);
    }

    public UserDetails loadUserByUserId(Long id) throws BadCredentialsException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BadCredentialsException(NOT_FOUND_USER));
        CustomUserInfoDto customUserInfoDto = CustomUserInfoDto.from(user);

        return new CustomUserDetails(customUserInfoDto);
    }
}