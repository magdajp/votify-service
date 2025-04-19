package com.votify.housingcommunity.addresident;

import com.goodcode.online.result.Result;
import com.votify.shared.Email;
import com.votify.shared.event.Event;
import com.votify.shared.result.Failure;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
class HousingCommunity {
    private final UUID id;
    private final UUID ownerId;
    private final Set<Email> residents;

    Result<UserAddedToHousingCommunity, Failure> addResident(User user, UUID byWho) {
        if (!ownerId.equals(byWho)) {
            return Result.failure(userHasNoPermissionToAddResident(user.id(), byWho));
        }
        if (!residents.add(user.email())) {
            return Result.failure(userIsAlreadyAddedToCommunity(user.id(), id));
        }
        return Result.success(new UserAddedToHousingCommunity(user, id));
    }

    public record UserAddedToHousingCommunity(User user, UUID housingCommunityId) implements Event {
        @Override
        public String payload() {
            return "User[%s] added to community[%s]".formatted(user.id(), housingCommunityId);
        }

        public UUID userId() {
            return user.id();
        }
    }

    private Failure userHasNoPermissionToAddResident(UUID residentId, UUID byWho) {
        return () -> "User[%s] has no permission to add resident[%s] to community[%s]".formatted(byWho, residentId, id);
    }

    private Failure userIsAlreadyAddedToCommunity(UUID userId, UUID communityId) {
        return () -> "User[%s] is already added to community[%s]".formatted(userId, communityId);
    }
}
