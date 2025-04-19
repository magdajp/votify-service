package com.votify.housingcommunity.resolution.vote;

import com.goodcode.online.result.Result;
import com.votify.housingcommunity.resolution.vote.Resolution.UserVotedForResolution;
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
class VoteForResolution {
    private static final Logger LOGGER = LoggerFactory.getLogger(VoteForResolution.class);

    @Qualifier("voteForResolutionResolutionRepo")
    private final ResolutionRepository resolutionRepository;
    private final Clock clock;
    private final EventPublisher eventPublisher;

    Result<UserVotedForResolution, Failure> voteForResolution(Resolution.VoteForResolutionCommand command) {
        return resolutionRepository.fetchById(command.resolutionId())
                .flatSuccess(resolution -> resolution.vote(clock, command))
                .ifSuccess(event -> resolutionRepository.save(event.resolution()))
                .ifSuccess(eventPublisher::publish)
                .ifFailure(failure -> LOGGER.error("Failed to vote for resolution[{}] by [{}]: {}", command.resolutionId(), command.byWho(), failure.message()));
    }

}
