package com.ethan.emsp.core.ddd;

import java.time.Instant;

public abstract class AppEvent {
    private final Instant occurredAt = Instant.now();

    public Instant getOccurredAt() {
        return occurredAt;
    }
}
