package com.votify.resident;

import com.goodcode.online.result.Result;
import com.votify.resident.HousingCommunity.UserAddedToHousingCommunity;
import com.votify.shared.event.EventPublisher;
import com.votify.shared.result.Failure;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AddResidentToHousingCommunity {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddResidentToHousingCommunity.class);

    @Qualifier("residentAddToCommunityHousingCommunityRepo")
    private final HousingCommunityRepository housingCommunityRepository;
    private final EventPublisher eventPublisher;

    Result<UserAddedToHousingCommunity, Failure> addHousingCommunity(AddUserCommand command) {
        return housingCommunityRepository.fetchById(command.communityId)
                .flatSuccess(community -> community.addResident(new User(command.userEmail, command.userFirstName, command.userLastName), command.byWho))
                .ifSuccess(housingCommunityRepository::persist)
                .ifSuccess(eventPublisher::publish)
                .ifFailure(failure -> LOGGER.error("Failed to add resident[%s] to community[%s]: %s".formatted(command.userEmail, command.communityId, failure.message())));
    }

    @Builder
    public record AddUserCommand(UUID communityId, String userFirstName, String userLastName, String userEmail,
                                 UUID byWho) {
    }
}
