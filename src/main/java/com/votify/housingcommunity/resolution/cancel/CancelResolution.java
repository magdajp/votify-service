package com.votify.housingcommunity.resolution.cancel;

import com.goodcode.online.result.Result;
import com.votify.housingcommunity.resolution.cancel.Resolution.ResolutionCancelled;
import com.votify.shared.event.EventPublisher;
import com.votify.shared.result.Failure;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class CancelResolution {
    private static final Logger LOGGER = LoggerFactory.getLogger(CancelResolution.class);

    @Qualifier("cancelResolutionResolutionRepo")
    private final ResolutionRepository resolutionRepository;
    private final EventPublisher eventPublisher;

    Result<ResolutionCancelled, Failure> cancelResolution(UUID resolutionId, UUID byWho) {
        return resolutionRepository.fetchById(resolutionId)
                .flatSuccess(resolution -> resolution.cancel(byWho))
                .ifSuccess(event -> resolutionRepository.save(event.resolution()))
                .ifSuccess(eventPublisher::publish)
                .ifFailure(failure -> LOGGER.error("Failed to cancel resolution[{}] by [{}]: {}", resolutionId, byWho, failure.message()));
    }

}
