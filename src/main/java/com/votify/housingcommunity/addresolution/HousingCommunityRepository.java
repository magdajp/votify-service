package com.votify.housingcommunity.addresolution;

import com.goodcode.online.result.Result;
import com.votify.shared.result.Failure;
import com.votify.shared.result.NotFoundFailure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("addResolutionHousingCommunityRepo")
interface HousingCommunityRepository extends JpaRepository<HousingCommunity, UUID> {
    default Result<HousingCommunity, Failure> fetchById(UUID id) {
        return findById(id)
                .map(Result::<HousingCommunity, Failure>success)
                .orElseGet(() -> Result.failure((NotFoundFailure) () -> "Housing community[%s] does not exist".formatted(id)));
    }
}
