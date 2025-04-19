package com.votify.housingcommunity.resolution.cancel;

import com.goodcode.online.result.Result;
import com.votify.shared.event.Event;
import com.votify.shared.result.Failure;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "CancelResolutionResolutionEntity")
@Table(name = "resolutions")
@Getter
class Resolution {
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private boolean deleted;
    @Column
    private LocalDateTime deadline;
    @ManyToOne
    @JoinColumn(name = "housing_community_id", referencedColumnName = "id")
    private HousingCommunity housingCommunity;

    Result<ResolutionCancelled, Failure> cancel(UUID byWho) {
        if (housingCommunity.isNotOwnedBy(byWho)) {
            return Result.failure(userHasNoPermissionToCancelResolution(byWho));
        }
        this.deleted = true;
        return Result.success(new ResolutionCancelled(this));
    }

    private Failure userHasNoPermissionToCancelResolution(UUID byWho) {
        return () -> "User[%s] has no permission to cancel resolution[%s]".formatted(byWho, id);
    }

    public record ResolutionCancelled(Resolution resolution) implements Event {
        @Override
        public String payload() {
            return "Resolution[%s] cancelled".formatted(resolution.id());
        }
    }
}
