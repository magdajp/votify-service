package com.votify.housingcommunity.addresolution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("addResolutionResolutionRepo")
interface ResolutionRepository extends JpaRepository<Resolution, UUID> {
}
