package com.ethan.emsp.domain.model.location;

import com.ethan.emsp.core.ddd.ValueObject;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LocationAttributes implements ValueObject {
    private String name;
    private String address;
    private Coordinate coordinate;
    private BusinessHours businessHours;
    private LocalDateTime createdAt;

    public void setBusinessHours(BusinessHours businessHours) {
        if (businessHours != null) {
            this.businessHours = businessHours;
        }
    }
}
