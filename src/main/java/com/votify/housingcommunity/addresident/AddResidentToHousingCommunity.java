package com.votify.housingcommunity.addresident;

import com.goodcode.online.result.Result;
import com.votify.housingcommunity.addresident.HousingCommunity.UserAddedToHousingCommunity;
import com.votify.shared.event.EventPublisher;
import com.votify.shared.result.Failure;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class AddResidentToHousingCommunity {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddResidentToHousingCommunity.class);

    @Qualifier("addResidentToCommunityHousingCommunityRepo")
    private final HousingCommunityRepository housingCommunityRepository;
    private final PasswordEncoder passwordEncoder;
    private final EventPublisher eventPublisher;

    Result<UserAddedToHousingCommunity, Failure> addHousingCommunity(AddUserCommand command) {
        return housingCommunityRepository.fetchById(command.communityId)
                .flatSuccess(community -> community.addResident(newUser(command), command.byWho))
                .flatSuccess(housingCommunityRepository::persist)
                .ifSuccess(eventPublisher::publish)
                .ifFailure(failure -> LOGGER.error("Failed to add resident[{}] to community[{}]: {}", command.userEmail, command.communityId, failure.message()));
    }

    private User newUser(AddUserCommand command) {
        return new User(
                command.userEmail,
                command.userFirstName,
                command.userLastName,
                passwordEncoder.encode(command.password)
        );
    }

    @Builder
    public record AddUserCommand(UUID communityId,
                                 String userFirstName,
                                 String userLastName,
                                 String userEmail,
                                 String password,
                                 UUID byWho) {
    }
}
