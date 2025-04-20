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
public class UserProvider {
    private final UserRepository userRepository;
    private final CommunityRepository communityRepository;

    public UUID userId() {
        return user().id();
    }

    public LoggedUser user() {
        return ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .map(principal -> (org.springframework.security.core.userdetails.User) principal)
                .flatMap(user -> userRepository.findByEmail(user.getUsername()))
                .map(this::toUser)
                .orElseThrow();
    }

    private LoggedUser toUser(User user) {
        var community = communityRepository.findByOwnerId(user.id());
        return LoggedUser.builder()
                .id(user.id())
                .email(user.email())
                .community(community.name())
                .role(community.roleInCommunity(user.id()))
                .build();
    }
}
