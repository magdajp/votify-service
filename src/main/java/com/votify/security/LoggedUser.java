package com.votify.security;

import lombok.Builder;

import java.util.UUID;

@Builder
public record LoggedUser(UUID id,
                         String email,
                         String firstName,
                         String lastName,
                         Role role,
                         UUID communityId,
                         String communityName) {
}
