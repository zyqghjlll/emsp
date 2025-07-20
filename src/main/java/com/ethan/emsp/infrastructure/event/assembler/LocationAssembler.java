package com.ethan.emsp.infrastructure.event.assembler;

import com.ethan.emsp.domain.event.LocationChangedEvent;
import com.ethan.emsp.infrastructure.persistence.event.po.LocationViewPO;

public class LocationAssembler {
    public static LocationViewPO toLocationView(LocationChangedEvent location) {
        LocationViewPO locationViewPO = new LocationViewPO();
        locationViewPO.setLocationId(location.getLocationId());
        locationViewPO.setName(location.getName());
        locationViewPO.setAddress(location.getAddress());
        locationViewPO.setLatitude(location.getLatitude());
        locationViewPO.setLongitude(location.getLongitude());
        locationViewPO.setOpenTime(location.getOpenTime());
        locationViewPO.setCloseTime(location.getCloseTime());
        locationViewPO.setUpdatedAt(location.getTimestamp());
        return locationViewPO;
    }
}
