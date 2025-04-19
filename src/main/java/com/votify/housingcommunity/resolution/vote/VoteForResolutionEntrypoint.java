package com.votify.housingcommunity.resolution.vote;

import com.votify.housingcommunity.resolution.vote.Resolution.VoteForResolutionCommand;
import com.votify.security.UserIdProvider;
import com.votify.shared.VoteOption;
import com.votify.shared.entrypoint.FailureResponse;
import com.votify.shared.entrypoint.SuccessResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/housing-community/resolution/{resolutionId}/vote")
@RequiredArgsConstructor
public class VoteForResolutionEntrypoint {
    private final UserIdProvider userIdProvider;
    private final VoteForResolution voteForResolution;

    @PutMapping
    public ResponseEntity<Object> voteForResolution(@PathVariable UUID resolutionId,
                                                    @Valid @RequestBody VoteForResolutionRequest request) {
        return voteForResolution.voteForResolution(request.toCommand(userIdProvider.userId(), resolutionId))
                .get(event -> SuccessResponse.empty(), FailureResponse::of);
    }

    public record VoteForResolutionRequest(@NotNull(message = "Vote is required") VoteOption vote) {

        private VoteForResolutionCommand toCommand(UUID byWho, UUID resolutionId) {
            return VoteForResolutionCommand
                    .builder()
                    .resolutionId(resolutionId)
                    .vote(vote)
                    .byWho(byWho)
                    .build();
        }
    }
}
