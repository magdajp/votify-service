package com.votify.query.fetchresolutions.admin;

import com.votify.security.UserProvider;
import com.votify.shared.entrypoint.FailureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("queryFetchResolutionForAdminResolutionEntrypoint")
@RequestMapping("/api/housing-community/resolution")
@RequiredArgsConstructor
public class ResolutionEntrypoint {
    private final UserProvider userProvider;
    private final FetchResolutions fetchResolutions;

    @GetMapping("/all")
    public ResponseEntity<Object> fetchAll() {
        return fetchResolutions.fetchCommunityResolutions(userProvider.userId())
                .get(ResponseEntity::ok, FailureResponse::of);
    }
}
