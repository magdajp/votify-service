package com.votify.housingcommunity.addresident;

import com.votify.security.UserProvider;
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
@RequestMapping("/api/housing-community/{communityId}/resident")
@RequiredArgsConstructor
public class ResidentEntrypoint {
    private final UserProvider userProvider;
    private final AddResidentToHousingCommunity addResidentToHousingCommunity;

    @PutMapping
    public ResponseEntity<Object> addResident(
            @PathVariable UUID communityId,
            @Valid @RequestBody AddResidentRequest request) {
        return addResidentToHousingCommunity.addHousingCommunity(request.toCommand(communityId, userProvider.userId()))
                .mapSuccess(HousingCommunity.UserAddedToHousingCommunity::userId)
                .get(SuccessResponse::created, FailureResponse::of);
    }

    public record AddResidentRequest(
            @NotNull(message = "User first name is required") String userFirstName,
            @NotNull(message = "User last name is required") String userLastName,
            @NotNull(message = "User email is required") String userEmail,
            @NotNull(message = "User password is required") String password) {

        private AddResidentToHousingCommunity.AddUserCommand toCommand(UUID communityId, UUID userId) {
            return AddResidentToHousingCommunity.AddUserCommand
                    .builder()
                    .userFirstName(userFirstName)
                    .userLastName(userLastName)
                    .userEmail(userEmail)
                    .communityId(communityId)
                    .password(password)
                    .byWho(userId)
                    .build();
        }
    }
}
