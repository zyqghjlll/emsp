package com.ethan.emsp.domain.model.location;

import java.time.LocalDateTime;

public record Equipment(
        String id,
        String status,
        LocalDateTime addedAt
) {
    public Equipment(String id, String status, LocalDateTime addedAt) {
        this.id = id;
        this.status = status;
        this.addedAt = addedAt;
    }
}
