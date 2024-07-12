package com.example.instagramclone.auth.service;

import com.example.instagramclone.auth.dto.CustomUserInfoDto;
import com.example.instagramclone.auth.model.CustomUserDetails;
import com.example.instagramclone.user.entity.User;
import com.example.instagramclone.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    private final static String NOT_FOUND_USER = "해당하는 유저가 없습니다.";

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND_USER));

        CustomUserInfoDto customUserInfoDto = modelMapper.map(user, CustomUserInfoDto.class);

        return new CustomUserDetails(customUserInfoDto);
    }
}
