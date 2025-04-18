package com.votify.shared.event;

import java.util.Collection;
import java.util.List;

public interface EventPublisher {
    default EventPublisher publish(Event event) {
        return publish(List.of(event));
    }

    EventPublisher publish(Collection<Event> event);
}
