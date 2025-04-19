package com.votify.query.fetchresolutions.resident;

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

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class FetchResolutions {
    private static final Logger LOGGER = LoggerFactory.getLogger(FetchResolutions.class);

    @Qualifier("queryFetchResolutionForResidentResolutionRepo")
    private final ResolutionRepository resolutionRepository;
    @Qualifier("queryFetchResolutionForResidentResidentRepo")
    private final ResidentRepository residentRepository;

    Result<List<Resolution>, Failure> fetchResidentsResolutions(UUID resident) {
        return residentRepository.fetchResidentCommunityId(resident)
                .mapSuccess(resolutionRepository::findAllByCommunityId)
                .ifFailure(failure -> LOGGER.error("Failed to fetch resident's[{}] resolutions: {}",resident, failure.message()));
    }
}
