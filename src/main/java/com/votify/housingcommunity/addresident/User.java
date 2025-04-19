package com.votify.housingcommunity.addresident;

import com.votify.shared.Email;

import java.util.UUID;

import static java.util.UUID.randomUUID;

record User(UUID id, Email email, String firstName, String lastName) {
    User(String email, String firstName, String lastName) {
        this(randomUUID(), new Email(email), firstName, lastName);
    }
}
