package com.votify.security;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserDetailsProvider {
    //TODO: to replace with JWT
    public UUID userId() {
        return UUID.fromString("c708ee2a-5b19-4cf8-ab3d-bbbc923ad4c2");
    }
}
