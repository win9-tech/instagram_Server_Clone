package com.example.instagramclone.auth.service;

import com.example.instagramclone.auth.model.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public Long getAuthenticatedId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof UserDetails) {
            return ((CustomUserDetails) authentication.getPrincipal()).getId();
        }
        return null;
    }
}