package com.votify.housingcommunity.resolution.vote;

import com.votify.shared.VoteOption;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "VoteForResolutionVoteEntity")
@Table(name = "votes")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
class Vote {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "resolution_id")
    private UUID resolutionId;
    @Column(name = "resident_id")
    private UUID residentId;
    @Enumerated(EnumType.STRING)
    @Column(name = "vote")
    private VoteOption voteOption;

    static Vote of(UUID resolutionId, UUID residentId, VoteOption option) {
        return new Vote(null, resolutionId, residentId, option);
    }

    boolean isVotedBy(UUID voter) {
        return residentId.equals(voter);
    }
}
