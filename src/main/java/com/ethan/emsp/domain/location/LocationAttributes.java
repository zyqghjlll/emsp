package com.ethan.emsp.domain.location;

import com.ethan.emsp.core.ddd.ValueObject;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LocationAttributes implements ValueObject {
    private String name;
    private String address;
    private String coordinates;
    private Long businessHours = 0L;
    private LocalDateTime createdAt;

    public void setBusinessHours(Long businessHours) {
        if (businessHours != null) {
            this.businessHours = businessHours;
        }
    }
}
