package com.votify.housingcommunity.create;

import com.votify.shared.event.Event;

import java.util.UUID;

record HousingCommunityCreated(UUID communityId, String communityName, UUID owner) implements Event {
    @Override
    public String payload() {
        return "Housing community[%s] created by [%s]".formatted(communityName, owner);
    }
}
