package com.ethan.emsp.core.ddd;

import lombok.Getter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
public abstract class AppEvent {
    private final String eventId;
    private final String operatorId;
    private final LocalDateTime timestamp;

    public AppEvent(String operatorId) {
        this.eventId = java.util.UUID.randomUUID().toString();
        this.operatorId = operatorId;
        this.timestamp = LocalDateTime.now();
    }
}
