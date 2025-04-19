package com.votify.housingcommunity.resolution.cancel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity(name = "CancelResolutionCommunityEntity")
@Table(name = "housing_communities")
class HousingCommunity {
    @Id
    private UUID id;
    @Column
    private UUID ownerId;

    boolean isNotOwnedBy(UUID byWho) {
        return !ownerId.equals(byWho);
    }
}
