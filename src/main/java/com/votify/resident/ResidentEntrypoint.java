package com.votify.resident;

import com.votify.security.UserIdProvider;
import com.votify.shared.entrypoint.FailureResponse;
import com.votify.shared.entrypoint.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/housing-community/{communityId}/resident")
@RequiredArgsConstructor
public class ResidentEntrypoint {
    private final UserIdProvider userIdProvider;
    private final AddResidentToHousingCommunity addResidentToHousingCommunity;

    @PutMapping
    public ResponseEntity<Object> addResident(
            @PathVariable UUID communityId,
            @RequestBody AddResidentRequest request) {
        return addResidentToHousingCommunity.addHousingCommunity(request.toCommand(communityId, userIdProvider.userId()))
                .mapSuccess(HousingCommunity.UserAddedToHousingCommunity::userId)
                .get(SuccessResponse::created, FailureResponse::of);
    }

    public record AddResidentRequest(String userFirstName, String userLastName, String userEmail) {

        private AddResidentToHousingCommunity.AddUserCommand toCommand(UUID communityId, UUID userId) {
            return AddResidentToHousingCommunity.AddUserCommand
                    .builder()
                    .userFirstName(userFirstName)
                    .userLastName(userLastName)
                    .userEmail(userEmail)
                    .communityId(communityId)
                    .byWho(userId)
                    .build();
        }
    }
}
