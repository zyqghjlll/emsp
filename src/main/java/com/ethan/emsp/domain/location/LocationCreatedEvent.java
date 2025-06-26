package com.ethan.emsp.domain.location;

import com.ethan.emsp.core.ddd.AppEvent;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LocationCreatedEvent extends AppEvent {
    private final String locationId;
    private final String locationName;

    public LocationCreatedEvent(String locationId, String locationName) {
        this.locationId = locationId;
        this.locationName = locationName;
    }
}
