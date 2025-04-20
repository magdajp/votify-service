package com.votify.security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String email);
}
