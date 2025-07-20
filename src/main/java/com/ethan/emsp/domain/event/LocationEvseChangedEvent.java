package com.ethan.emsp.domain.event;

import com.ethan.emsp.core.ddd.AppEvent;
import com.ethan.emsp.domain.model.evse.EvseId;
import com.ethan.emsp.domain.model.location.LocationId;
import lombok.Getter;

@Getter
public class LocationEvseChangedEvent extends AppEvent {
    private final String evseId;
    private final String locationId;

    private LocationEvseChangedEvent(String evseId, String locationId) {
        super("");
        this.evseId = evseId;
        this.locationId = locationId;
    }

    public static LocationEvseChangedEvent of(String evseId, String locationId) {
        return new LocationEvseChangedEvent(evseId, locationId);
    }
}
