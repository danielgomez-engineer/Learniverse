package com.danieldev.Learniverse.impl;

import com.danieldev.Learniverse.model.Rol;
import com.danieldev.Learniverse.model.User;
import com.danieldev.Learniverse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).
                orElseThrow (() -> new UsernameNotFoundException("usuario no encontrado con email: " + email + "impl/UserDetailsServiceimpl/loadUserByUsername."));

    }
}
