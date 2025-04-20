package com.votify.query.fetchresolutions.resident;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("queryFetchResolutionForResidentVoteRepo")
interface VoteRepository extends JpaRepository<Vote, UUID> {
    List<Vote> findAllByResidentId(UUID id);
}
