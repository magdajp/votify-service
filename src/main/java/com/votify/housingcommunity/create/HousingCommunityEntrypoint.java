package com.votify.housingcommunity.create;

import com.votify.security.UserIdProvider;
import com.votify.shared.entrypoint.FailureResponse;
import com.votify.shared.entrypoint.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/housing-community")
@RequiredArgsConstructor
public class HousingCommunityEntrypoint {
    private final UserIdProvider userIdProvider;
    private final CreateHousingCommunity createHousingCommunity;
    private final FetchHousingCommunity fetchHousingCommunity;

    @PostMapping("/create")
    public ResponseEntity<Object> createCommunity(@RequestBody CreateCommunityRequest request) {
        return createHousingCommunity.createHousingCommunity(request.toCommand(userIdProvider.userId()))
                .mapSuccess(HousingCommunityCreated::communityId)
                .get(SuccessResponse::created, FailureResponse::of);
    }

    @GetMapping("/all")
    public List<HousingCommunityDto> fetchAll() {
        return fetchHousingCommunity.fetchAll().stream()
                .map(this::toDto)
                .toList();
    }

    public record CreateCommunityRequest(String name, String location) {

        private CreateHousingCommunity.Command toCommand(UUID userId) {
            return CreateHousingCommunity.Command
                    .builder()
                    .name(name)
                    .location(location)
                    .owner(userId)
                    .build();
        }
    }

    public record HousingCommunityDto(UUID id, String name, String location, UUID owner) {
    }

    private HousingCommunityDto toDto(HousingCommunity housingCommunity) {
        return new HousingCommunityDto(
                housingCommunity.id(),
                housingCommunity.name(),
                housingCommunity.location(),
                housingCommunity.ownerId()
        );
    }
}
