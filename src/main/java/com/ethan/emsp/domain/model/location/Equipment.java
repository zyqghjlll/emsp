package com.ethan.emsp.domain.model.location;

import java.time.LocalDateTime;

public record Equipment(
        String id,
        String status,
        LocalDateTime createdAt
) {
    public Equipment(String id, String status, LocalDateTime createdAt) {
        this.id = id;
        this.status = status;
        this.createdAt = createdAt;
    }
}
