package com.votify.shared;

import java.time.Instant;

public interface Clock {
    default Instant currentTime() {
        return Instant.now();
    }
}
