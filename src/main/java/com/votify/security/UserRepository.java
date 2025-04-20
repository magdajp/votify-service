package com.votify.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);

    @Query(nativeQuery = true, value = "SELECT CASE WHEN COUNT(c) > 0 THEN TRUE ELSE FALSE END FROM housing_communities c WHERE c.owner_id = :ownerId")
    boolean isAdmin(@Param("ownerId") UUID userId);
}
