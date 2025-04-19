package com.votify.housingcommunity.addresolution;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "resolutions")
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
class Resolution {
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private UUID housingCommunityId;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private int quorum;
    @Column
    private LocalDateTime deadline;
}
