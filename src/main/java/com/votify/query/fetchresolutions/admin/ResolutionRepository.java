package com.votify.query.fetchresolutions.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("queryFetchResolutionForAdminResolutionRepo")
interface ResolutionRepository extends JpaRepository<Resolution, UUID> {
    List<Resolution> findAllByCommunityId(UUID communityId);
}
