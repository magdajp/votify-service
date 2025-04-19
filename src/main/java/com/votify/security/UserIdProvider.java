package com.votify.security;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class UserIdProvider {
    private final UserRepository userRepository;

    //TODO: to replace with JWT
    public UUID userId() {
        return userRepository.findByEmail("peter.griffin@quahog.com").orElseThrow().id();
    }
}
