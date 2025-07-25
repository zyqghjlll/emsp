package com.ethan.emsp.infrastructure.persistence.query.bo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LocationQueryBO {
    private String locationId;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String openTime;
    private String closeTime;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
