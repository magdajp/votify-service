package com.votify.query.fetchresolutions.resident;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "QueryFetchResolutionForResidentResolutionEntity")
@Table(name = "residents_resolutions_view")
@NoArgsConstructor
@Getter
@Accessors(fluent = false, chain = true)
public class Resolution {
    @Id
    private UUID id;
    @Column
    private UUID communityId;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private LocalDateTime deadline;
    @Column
    private String status;
}
