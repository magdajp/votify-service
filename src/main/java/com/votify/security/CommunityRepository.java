package com.votify.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

interface CommunityRepository extends JpaRepository<Community, UUID> {
    @Query(nativeQuery = true, value = """
            SELECT id, name, owner_id
            FROM housing_communities c
            WHERE c.owner_id = :userId
            UNION
            DISTINCT
            SELECT c.id, c.name, c.owner_id
            FROM residents r
                     INNER JOIN public.housing_communities c on r.housing_community_id = c.id
            WHERE r.user_id = :userId
            """)
    Optional<Community> findCommunity(@Param("userId") UUID userId);
}
