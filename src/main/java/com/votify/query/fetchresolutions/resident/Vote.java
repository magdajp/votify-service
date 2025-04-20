package com.votify.query.fetchresolutions.resident;

import com.votify.shared.VoteOption;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "QueryFetchResolutionForResidentVoteEntity")
@Table(name = "votes")
@NoArgsConstructor
@Getter
class Vote {
    @Id
    private UUID id;
    @Column(name = "resolution_id")
    private UUID resolutionId;
    @Column(name = "resident_id")
    private UUID residentId;
    @Column(name = "vote")
    private String voteOption;

    public VoteOption vote() {
        return VoteOption.valueOf(voteOption);
    }
}
