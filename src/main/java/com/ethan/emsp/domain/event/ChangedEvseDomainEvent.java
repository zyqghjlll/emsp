package com.ethan.emsp.domain.event;

import com.ethan.emsp.core.ddd.AppEvent;
import com.ethan.emsp.domain.model.evse.EvseId;
import com.ethan.emsp.domain.model.evse.EvseStatus;
import com.ethan.emsp.domain.model.location.LocationId;
import lombok.Getter;

@Getter
public class ChangedEvseDomainEvent extends AppEvent {
    private final EvseId id;
    private final LocationId locationId;
    private final EvseStatus status;

    public ChangedEvseDomainEvent(EvseId id, LocationId locationId, EvseStatus status) {
        this.id = id;
        this.locationId = locationId;
        this.status = status;
    }
}
