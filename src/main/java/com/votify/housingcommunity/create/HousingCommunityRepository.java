package com.votify.housingcommunity.create;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface HousingCommunityRepository extends JpaRepository<HousingCommunity, UUID> {
}
