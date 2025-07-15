package com.ethan.emsp.api.controller.vo;

public record LocationVo(
        String id,
        String name,
        String address,
        String latitude,
        String longitude,
        String openTime,
        String closeTime
) {
}
