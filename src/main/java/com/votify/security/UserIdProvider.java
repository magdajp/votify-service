package com.votify.security;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class UserIdProvider {
    private final UserRepository userRepository;
    public UUID userId() {
        return ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .map(principal -> (org.springframework.security.core.userdetails.User) principal)
                .flatMap(user -> userRepository.findByEmail(user.getUsername()))
                .map(User::id)
                .orElseThrow();
    }

}
