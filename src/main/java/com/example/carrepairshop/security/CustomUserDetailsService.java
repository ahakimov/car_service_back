package com.example.carrepairshop.security;

import com.example.carrepairshop.model.User;
import com.example.carrepairshop.security.user.CustomUserDetails;
import com.example.carrepairshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.findByEmail(username)
                .orElseThrow();
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
        return new CustomUserDetails(user.getId(), user.getPassword(), user.getEmail(), authorities);
    }
}
