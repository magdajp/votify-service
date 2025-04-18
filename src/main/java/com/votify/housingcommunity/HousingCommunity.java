package com.votify.housingcommunity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@Entity
@Table(name = "housing_communities")
@NoArgsConstructor
@Setter
@Getter
class HousingCommunity {
    @Id
    private UUID id;
    @Column
    private String name;
    @Column
    private String location;
    @Column
    private UUID ownerId;

    static HousingCommunity newCommunity(String name, String location, UUID ownerId) {
        return new HousingCommunity()
                .id(randomUUID())
                .name(name)
                .location(location)
                .ownerId(ownerId);
    }
}
