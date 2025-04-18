package com.votify.shared.event;

import java.time.Instant;

public interface Event {
    String payload();

    default Instant timestamp() {
        return Instant.now();
    }
}
