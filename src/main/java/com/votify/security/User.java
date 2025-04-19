package com.votify.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
class User {
    @Id
    @Column
    private UUID id;

    @Column
    private String email;

    @Column
    private String password;
}
