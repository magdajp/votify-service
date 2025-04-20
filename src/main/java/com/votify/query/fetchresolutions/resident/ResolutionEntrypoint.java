package com.votify.query.fetchresolutions.resident;

import com.votify.security.UserProvider;
import com.votify.shared.entrypoint.FailureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("queryFetchResolutionForResidentResolutionEntrypoint")
@RequestMapping("/api/resident/resolution")
@RequiredArgsConstructor
public class ResolutionEntrypoint {
    private final UserProvider userProvider;
    private final FetchResolutions fetchResolutions;

    @GetMapping("/all")
    public ResponseEntity<Object> fetchAll() {
        return fetchResolutions.fetchResidentsResolutions(userProvider.userId())
                .get(ResponseEntity::ok, FailureResponse::of);
    }
}
