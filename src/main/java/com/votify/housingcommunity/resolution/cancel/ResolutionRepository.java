package com.votify.housingcommunity.resolution.cancel;

import com.goodcode.online.result.Result;
import com.votify.shared.result.Failure;
import com.votify.shared.result.NotFoundFailure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("cancelResolutionResolutionRepo")
interface ResolutionRepository extends JpaRepository<Resolution, UUID> {
    default Result<Resolution, Failure> fetchById(UUID id) {
        return findById(id)
                .map(Result::<Resolution, Failure>success)
                .orElseGet(() -> Result.failure((NotFoundFailure) () -> "Resolution[%s] does not exist".formatted(id)));
    }
}
