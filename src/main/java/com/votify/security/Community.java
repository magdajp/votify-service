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
@Table(name = "housing_communities")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
class Community {
    @Id
    private UUID id;
    @Column
    private String name;
    @Column
    private UUID ownerId;

    Role roleInCommunity(UUID user) {
        return doesUserOwnCommunity(user) ? Role.ADMIN : Role.RESIDENT;
    }

    boolean doesUserOwnCommunity(UUID user) {
        return ownerId.equals(user);
    }
}
