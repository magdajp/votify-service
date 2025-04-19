package com.votify.housingcommunity.resolution.cancel;

import com.votify.security.UserIdProvider;
import com.votify.shared.entrypoint.FailureResponse;
import com.votify.shared.entrypoint.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/housing-community/resolution/{resolutionId}")
@RequiredArgsConstructor
public class CancelResolutionEntrypoint {
    private final UserIdProvider userIdProvider;
    private final CancelResolution cancelResolution;

    @PutMapping
    public ResponseEntity<Object> cancelResolution(@PathVariable UUID resolutionId) {
        return cancelResolution.cancelResolution(resolutionId, userIdProvider.userId())
                .get(resolutionCancelled -> SuccessResponse.empty(), FailureResponse::of);
    }
}
