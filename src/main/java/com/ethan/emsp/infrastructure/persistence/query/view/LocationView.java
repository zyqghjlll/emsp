package com.ethan.emsp.infrastructure.persistence.query.view;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LocationView {
    private String id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private String openTime;
    private String closeTime;
    private LocalDateTime createdAt;
}
