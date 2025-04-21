package com.votify.housingcommunity.addresolution;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.votify.housingcommunity.addresolution.HousingCommunity.ResolutionAdded;
import com.votify.security.UserProvider;
import com.votify.shared.entrypoint.FailureResponse;
import com.votify.shared.entrypoint.SuccessResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/housing-community/{communityId}/resolution")
@RequiredArgsConstructor
public class AddResolutionEntrypoint {
    private final UserProvider userProvider;
    private final AddResolution addResolution;

    @PostMapping
    public ResponseEntity<Object> addResolution(
            @PathVariable UUID communityId,
            @Valid @RequestBody CreateResolutionRequest request) {
        return addResolution.addResolution(request.toCommand(communityId, userProvider.userId()))
                .mapSuccess(ResolutionAdded::resolutionId)
                .get(SuccessResponse::created, FailureResponse::of);
    }

    public record CreateResolutionRequest(
            @NotNull(message = "Title is required") String title,
            @NotNull(message = "Content is required") String content,
            @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm") @NotNull(message = "Deadline is required") LocalDateTime deadline) {

        private HousingCommunity.AddResolutionCommand toCommand(UUID communityId, UUID byWho) {
            return HousingCommunity.AddResolutionCommand
                    .builder()
                    .communityId(communityId)
                    .title(title)
                    .content(content)
                    .deadline(deadline)
                    .byWho(byWho)
                    .build();
        }
    }
}
