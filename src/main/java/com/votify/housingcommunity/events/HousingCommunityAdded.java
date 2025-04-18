package com.votify.housingcommunity.events;

import com.votify.shared.event.Event;

import java.util.UUID;

public record HousingCommunityAdded(UUID communityId, String communityName, UUID owner) implements Event {
    @Override
    public String payload() {
        return "Housing community[%s] added by [%s]".formatted(communityName, owner);
    }
}
