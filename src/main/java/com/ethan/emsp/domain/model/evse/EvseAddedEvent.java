package com.ethan.emsp.domain.model.evse;

import com.ethan.emsp.core.ddd.AppEvent;
import lombok.Getter;

@Getter
public class EvseAddedEvent extends AppEvent {
    private final String locationId;
    private final String evseId;

    public EvseAddedEvent(String locationId, String evseId) {
        this.locationId = locationId;
        this.evseId = evseId;
    }
}
