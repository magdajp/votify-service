package com.votify.security;

import com.votify.shared.event.Event;

import java.util.UUID;

public record UserCreated(UUID userId,
                          String email,
                          String communityName,
                          String communityLocation) implements Event {
    @Override
    public String payload() {
        return "User[%s] created".formatted(userId);
    }
}
