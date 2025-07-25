package com.ethan.emsp.api.controller.vo;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record LocationVo(
        String id,
        String name,
        String address,
        String latitude,
        String longitude,
        String openTime,
        String closeTime,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
