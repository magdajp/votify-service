package com.votify.housingcommunity.resolution.vote;

import com.goodcode.online.result.Result;
import com.votify.shared.Clock;
import com.votify.shared.VoteOption;
import com.votify.shared.event.Event;
import com.votify.shared.result.Failure;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.time.ZoneOffset.UTC;

@Entity(name = "VoteForResolutionResolutionEntity")
@Table(name = "resolutions")
@Getter
class Resolution {
    @Id
    private UUID id;
    @Column
    private LocalDateTime deadline;
    @Column
    private boolean deleted;
    @ManyToOne
    @JoinColumn(name = "housing_community_id", referencedColumnName = "id")
    private HousingCommunity housingCommunity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "resolution_id", referencedColumnName = "id")
    private Set<Vote> votes = new HashSet<>();

    Result<UserVotedForResolution, Failure> vote(Clock clock, VoteForResolutionCommand command) {
        if (userHasNoPermissionToVote(command.byWho)) {
            return Result.failure(userHasNoPermissionToVoteForResolution(command.byWho));
        } else if (isVotingEnded(clock)) {
            return Result.failure(votingForResolutionHasBeenEnded(command.byWho));
        } else if (userHasAlreadyVoted(command.byWho)) {
            return Result.failure(userAlreadyVotedForResolution(command.byWho));
        }
        votes.add(Vote.of(id, command.byWho, command.vote));
        return Result.success(new UserVotedForResolution(command.byWho, this));
    }

    private boolean userHasNoPermissionToVote(UUID voter) {
        return !housingCommunity.isPermittedToVote(voter);
    }

    private boolean isVotingEnded(Clock clock) {
        return clock.currentTime().isAfter(deadline.toInstant(UTC));
    }

    private boolean userHasAlreadyVoted(UUID voter) {
        return votes.stream().anyMatch(vote -> vote.isVotedBy(voter));
    }

    private Failure userHasNoPermissionToVoteForResolution(UUID voter) {
        return () -> "User[%s] has no permission to vote for resolution[%s]".formatted(voter, id);
    }

    private Failure votingForResolutionHasBeenEnded(UUID voter) {
        return () -> "User[%s] can not vote for resolution[%s] which has been ended".formatted(voter, id);
    }

    private Failure userAlreadyVotedForResolution(UUID voter) {
        return () -> "User[%s] already voted for resolution[%s]".formatted(voter, id);
    }

    boolean isNotCancelled() {
        return !deleted;
    }

    public record UserVotedForResolution(UUID byWho, Resolution resolution) implements Event {
        @Override
        public String payload() {
            return "User[%s] voted for resolution[%s] ".formatted(byWho, resolution.id());
        }
    }

    @Builder
    public record VoteForResolutionCommand(UUID byWho, UUID resolutionId, VoteOption vote) {

    }
}
