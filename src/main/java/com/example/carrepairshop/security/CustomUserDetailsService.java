package com.example.carrepairshop.security;

import com.example.carrepairshop.model.Client;
import com.example.carrepairshop.security.user.CustomUserDetails;
import com.example.carrepairshop.service.ClientService;
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
    private final ClientService clientService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Client client = clientService.findByEmail(username)
                .orElseThrow();
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(client.getRole()));
        return new CustomUserDetails(client.getId(), "", client.getPassword(), client.getEmail(), authorities);
    }
}
