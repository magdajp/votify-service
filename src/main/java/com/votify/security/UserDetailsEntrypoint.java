package com.votify.security;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/details")
@RequiredArgsConstructor
class UserDetailsEntrypoint {
    private final UserProvider userProvider;

    @GetMapping
    public DetailsResponse details() {
        var user = userProvider.user();
        return new DetailsResponse(user.email(), user.role().name(), user.community());
    }

    public record DetailsResponse(String email, String role, String communityName) {

    }
}

