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
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class FetchResolutions {
    private static final Logger LOGGER = LoggerFactory.getLogger(FetchResolutions.class);

    @Qualifier("queryFetchResolutionForResidentResolutionRepo")
    private final ResolutionRepository resolutionRepository;
    @Qualifier("queryFetchResolutionForResidentResidentRepo")
    private final ResidentRepository residentRepository;
    @Qualifier("queryFetchResolutionForResidentVoteRepo")
    private final VoteRepository voteRepository;

    Result<List<Resolution>, Failure> fetchResidentsResolutions(UUID resident) {
        return residentRepository.fetchResidentCommunityId(resident)
                .mapSuccess(resolutionRepository::findAllByCommunityId)
                .mapSuccess(resolutions -> enhanceByResidentVotes(resolutions, resident))
                .ifFailure(failure -> LOGGER.error("Failed to fetch resident's[{}] resolutions: {}", resident, failure.message()));
    }

    private List<Resolution> enhanceByResidentVotes(List<Resolution> resolutions, UUID resident) {
        var resolutionById = resolutions.stream()
                .collect(Collectors.toMap(Resolution::getId, Function.identity()));
        var voteByResolutionId = voteRepository.findAllByResidentId(resident)
                .stream()
                .collect(Collectors.toMap(Vote::resolutionId, Function.identity()));
        voteByResolutionId.forEach((resolutionId, vote) -> resolutionById.get(resolutionId).addVoteOption(vote.vote()));
        return resolutions;
    }
}
