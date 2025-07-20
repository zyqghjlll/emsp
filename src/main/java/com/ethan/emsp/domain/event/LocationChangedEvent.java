package com.ethan.emsp.domain.event;

import com.ethan.emsp.core.ddd.AppEvent;
import com.ethan.emsp.domain.model.location.Location;
import com.ethan.emsp.infrastructure.persistence.domain.po.LocationPO;
import lombok.Getter;

@Getter
public class LocationChangedEvent extends AppEvent {
    private final String locationId;
    private final String name;
    private final String address;
    private final Double latitude;
    private final Double longitude;
    private final String openTime;
    private final String closeTime;

    private LocationChangedEvent(Location location) {
        super("");
        this.locationId = location.getId().getValue();
        this.name = location.getAttributes().getName();
        this.address = location.getAttributes().getAddress();
        this.latitude = location.getAttributes().getCoordinate().latitude();
        this.longitude = location.getAttributes().getCoordinate().longitude();
        this.openTime = location.getAttributes().getBusinessHours().openTime().toString();
        this.closeTime = location.getAttributes().getBusinessHours().closeTime().toString();
    }

    public static LocationChangedEvent of(Location location) {
        return new LocationChangedEvent(location);
    }
}
