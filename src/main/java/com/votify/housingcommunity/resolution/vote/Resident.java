package com.votify.housingcommunity.resolution.vote;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity(name = "VoteForResolutionResidentEntity")
@Table(name = "residents")
class Resident {
    @Id
    private UUID userId;

    boolean isOfId(UUID id) {
        return userId.equals(id);
    }
}
