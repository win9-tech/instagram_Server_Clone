package com.example.instagramclone.auth.service;

import com.example.instagramclone.auth.util.JwtUtil;
import com.example.instagramclone.auth.dto.CustomUserInfoDto;
import com.example.instagramclone.auth.dto.LoginRequestDto;
import com.example.instagramclone.domain.user.entity.User;
import com.example.instagramclone.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    private static final String USER_NOT_FOUND_MESSAGE = "존재하지 않는 username 입니다.";
    private static final String INCORRECT_PASSWORD_MESSAGE = "비밀번호가 일치하지 않습니다.";

    @Override
    public String login(LoginRequestDto request) throws UsernameNotFoundException, BadCredentialsException {

        User user = validateUser(request.getUsername(), request.getPassword());
        CustomUserInfoDto customUserInfoDto = CustomUserInfoDto.from(user);
        return jwtUtil.createAccessToken(customUserInfoDto);
    }

    private User validateUser(String username, String password) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE));

        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException(INCORRECT_PASSWORD_MESSAGE);
        }
        return user;
    }
}