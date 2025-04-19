package com.votify.housingcommunity.addresident;

import com.votify.shared.Email;

import java.util.UUID;

import static java.util.UUID.randomUUID;

record User(UUID id, Email email, String firstName, String lastName, String password) {
    User(String email, String firstName, String lastName, String password) {
        this(randomUUID(), new Email(email), firstName, lastName, password);
    }
}
