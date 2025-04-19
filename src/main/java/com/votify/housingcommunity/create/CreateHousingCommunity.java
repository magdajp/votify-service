package com.votify.housingcommunity.create;

import com.goodcode.online.result.Result;
import com.votify.shared.event.EventPublisher;
import com.votify.shared.result.Failure;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class CreateHousingCommunity {
    private final HousingCommunityRepository housingCommunityRepository;
    private final EventPublisher eventPublisher;

    Result<HousingCommunityCreated, Failure> createHousingCommunity(Command command) {
        var community = HousingCommunity.newCommunity(command.name, command.location, command.owner);
        housingCommunityRepository.save(community);

        var event = new HousingCommunityCreated(community.id(), community.name(), community.ownerId());
        eventPublisher.publish(event);
        return Result.success(event);
    }

    @Builder
    public record Command(String name, String location, UUID owner) {
    }
}
