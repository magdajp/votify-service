package com.votify.security;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/user/details")
@RequiredArgsConstructor
class UserDetailsEntrypoint {
    private final UserProvider userProvider;

    @GetMapping
    public DetailsResponse details() {
        var user = userProvider.user();
        return DetailsResponse.builder()
                .email(user.email())
                .firstName(user.firstName())
                .lastName(user.lastName())
                .role(user.role().name())
                .communityId(user.communityId())
                .communityName(user.communityName())
                .build();

    }

    @Builder
    public record DetailsResponse(String email,
                                  String firstName,
                                  String lastName,
                                  String role,
                                  UUID communityId,
                                  String communityName) {

    }
}

