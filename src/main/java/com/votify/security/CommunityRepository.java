package com.votify.security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface CommunityRepository extends JpaRepository<Community, UUID> {
    Community findByOwnerId(UUID userId);
}
