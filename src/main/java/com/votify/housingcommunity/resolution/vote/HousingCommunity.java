package com.votify.housingcommunity.resolution.vote;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity(name = "VoteForResolutionCommunityEntity")
@Table(name = "housing_communities")
class HousingCommunity {
    @Id
    private UUID id;
    @OneToMany
    @JoinColumn(name = "housing_community_id", referencedColumnName = "id")
    private Set<Resident> residents = new HashSet<>();

    boolean isPermittedToVote(UUID voter) {
        return residents.stream()
                .anyMatch(resident -> resident.isOfId(voter));
    }
}
