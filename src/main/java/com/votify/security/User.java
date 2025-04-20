package com.votify.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
class User {
    @Id
    @Column
    private UUID id;

    @Column
    private String email;

    @Column
    private String password;

    static User newUser(String email, String password) {
        return new User(UUID.randomUUID(), email, password);
    }
}
