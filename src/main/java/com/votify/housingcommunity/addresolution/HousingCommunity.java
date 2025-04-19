package com.votify.housingcommunity.addresolution;

import com.goodcode.online.result.Result;
import com.votify.shared.Clock;
import com.votify.shared.event.Event;
import com.votify.shared.result.Failure;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.time.ZoneOffset.UTC;

@Entity(name = "AddResolutionToCommunityHousingCommunityEntity")
@Table(name = "housing_communities_with_count")
@NoArgsConstructor
@Setter
@Getter
class HousingCommunity {
    @Id
    private UUID id;
    @Column
    private UUID ownerId;
    @Column
    private int numberOfResidents;

    Result<ResolutionAdded, Failure> addResolution(AddResolutionCommand command, Clock clock) {
        if (!ownerId.equals(command.byWho)) {
            return Result.failure(userHasNoPermissionToAddResolution(command.byWho, command.title));
        }
        if (clock.currentTime().isAfter(effectiveDeadline(command.deadline))) {
            return Result.failure(resolutionDeadlineExceeded(command.deadline));
        }

        var resolution = Resolution.builder()
                .communityId(id)
                .title(command.title)
                .content(command.content)
                .quorum(quorum(command.minQuorumInPercentage))
                .deadline(command.deadline)
                .build();
        return Result.success(new ResolutionAdded(id, resolution));
    }

    private static Instant effectiveDeadline(LocalDateTime deadline) {
        return deadline.minusDays(2).toInstant(UTC);
    }

    private int quorum(long minQuorumInPercentage) {
        return (int) Math.max(1, numberOfResidents * (Math.clamp(minQuorumInPercentage, 1, 100) / 100.0));
    }

    public record ResolutionAdded(UUID communityId, Resolution resolution) implements Event {
        @Override
        public String payload() {
            return "Resolution[%s] added for community[%s]".formatted(resolution.title(), communityId);
        }

        public UUID resolutionId() {
            return resolution.id();
        }
    }

    private Failure userHasNoPermissionToAddResolution(UUID byWho, String resolutionTitle) {
        return () -> "User[%s] has no permission to add resolution[%s] to community[%s]".formatted(byWho, resolutionTitle, id);
    }

    private Failure resolutionDeadlineExceeded(LocalDateTime deadline) {
        return () -> "Resolution deadline[%s] can't be earlier than two days from now".formatted(deadline);
    }

    @Builder
    public record AddResolutionCommand(UUID communityId,
                                       String title,
                                       String content,
                                       LocalDateTime deadline,
                                       int minQuorumInPercentage,
                                       UUID byWho) {
    }
}
