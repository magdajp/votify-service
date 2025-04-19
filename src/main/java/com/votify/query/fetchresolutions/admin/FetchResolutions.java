package com.votify.query.fetchresolutions.admin;

import com.goodcode.online.result.Result;
import com.votify.shared.result.Failure;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("queryFetchResolutionForAdminUsecase")
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class FetchResolutions {
    private static final Logger LOGGER = LoggerFactory.getLogger(FetchResolutions.class);

    @Qualifier("queryFetchResolutionForAdminResolutionRepo")
    private final ResolutionRepository resolutionRepository;
    @Qualifier("queryFetchResolutionForAdminCommunityRepo")
    private final CommunityRepository communityRepository;

    Result<List<Resolution>, Failure> fetchCommunityResolutions(UUID ownerId) {
        return communityRepository.fetchAdminCommunityId(ownerId)
                .mapSuccess(resolutionRepository::findAllByCommunityId)
                .ifFailure(failure -> LOGGER.error("Failed to fetch community owner's[{}] resolutions: {}",ownerId, failure.message()));
    }
}
