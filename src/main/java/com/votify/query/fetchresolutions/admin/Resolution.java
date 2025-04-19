package com.votify.query.fetchresolutions.admin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "QueryFetchResolutionForAdminResolutionEntity")
@Table(name = "community_resolutions_view")
@NoArgsConstructor
@Accessors(fluent = false, chain = true)
public class Resolution {
    @Getter
    @Id
    private UUID id;
    @Column
    @Getter
    private UUID communityId;
    @Column
    @Getter
    private String title;
    @Column
    @Getter
    private String content;
    @Column
    @Getter
    private int quorum;
    @Column
    private int numberOfResidents;
    @Column
    private int inFavor;
    @Column
    private int against;
    @Column
    @Getter
    private LocalDateTime deadline;
    @Column
    @Getter
    private String status;

    public Voting getVoting() {
        return new Voting(inFavor, against, numberOfResidents);
    }

    public record Voting(int inFavor, int against, int numberOfResidents) {

        public int getWithheld() {
            return numberOfResidents - inFavor + against;
        }

        public double getWithheldInPercent() {
            return inPercentage(getWithheld());
        }

        private double inPercentage(int value) {
            return Math.round(100f * value / Math.max(1, numberOfResidents));
        }

        public double getInFavorInPercent() {
            return inPercentage(inFavor);
        }

        public double getAgainstInPercent() {
            return inPercentage(against);
        }
    }
}
