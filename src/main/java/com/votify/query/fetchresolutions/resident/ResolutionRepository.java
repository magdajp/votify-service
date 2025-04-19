package com.votify.query.fetchresolutions.resident;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("queryFetchResolutionForResidentResolutionRepo")
interface ResolutionRepository extends JpaRepository<Resolution, UUID> {
    List<Resolution> findAllByCommunityId(UUID communityId);
}
