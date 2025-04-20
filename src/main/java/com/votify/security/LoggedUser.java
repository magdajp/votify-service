package com.votify.security;

import java.util.UUID;

public record LoggedUser(UUID id, Role role) {
}
