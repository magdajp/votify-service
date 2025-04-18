package com.votify.housingcommunity;

import com.votify.housingcommunity.events.HousingCommunityAdded;
import com.votify.security.UserDetailsProvider;
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
    private final UserDetailsProvider userDetailsProvider;
    private final AddHousingCommunity addHousingCommunity;
    private final FetchHousingCommunity fetchHousingCommunity;

    @PostMapping("/add")
    public ResponseEntity<Object> addCommunity(@RequestBody AddCommunityRequest request) {
        return addHousingCommunity.addHousingCommunity(request.toCommand(userDetailsProvider.userId()))
                .mapSuccess(HousingCommunityAdded::communityId)
                .get(SuccessResponse::created, FailureResponse::of);
    }

    @GetMapping("/all")
    public List<HousingCommunityDto> getAll() {
        return fetchHousingCommunity.fetchAll().stream()
                .map(this::toDto)
                .toList();
    }

    public record AddCommunityRequest(String name, String location) {

        private AddHousingCommunity.Command toCommand(UUID userId) {
            return AddHousingCommunity.Command
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
