package com.votify.housingcommunity.addresolution;

import com.goodcode.online.result.Result;
import com.votify.housingcommunity.addresolution.HousingCommunity.AddResolutionCommand;
import com.votify.housingcommunity.addresolution.HousingCommunity.ResolutionAdded;
import com.votify.shared.Clock;
import com.votify.shared.event.EventPublisher;
import com.votify.shared.result.Failure;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AddResolution {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddResolution.class);

    @Qualifier("addResolutionHousingCommunityRepo")
    private final HousingCommunityRepository housingCommunityRepository;
    @Qualifier("addResolutionResolutionRepo")
    private final ResolutionRepository resolutionRepository;
    private final Clock clock;
    private final EventPublisher eventPublisher;

    Result<ResolutionAdded, Failure> addResolution(AddResolutionCommand command) {
        return housingCommunityRepository.fetchById(command.communityId())
                .flatSuccess(community -> community.addResolution(command, clock))
                .ifSuccess(event -> resolutionRepository.save(event.resolution()))
                .ifSuccess(eventPublisher::publish)
                .ifFailure(failure -> LOGGER.error("Failed to add resolution[%s] to community[%s]: %s".formatted(command.title(), command.communityId(), failure.message())));
    }

}
