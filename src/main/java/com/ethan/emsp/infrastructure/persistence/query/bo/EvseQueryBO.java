package com.ethan.emsp.infrastructure.persistence.query.bo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EvseQueryBO {
    private String locationId;
    private String evseId;
    private String evseStatus;
    private LocalDateTime lastUpdated;
}
