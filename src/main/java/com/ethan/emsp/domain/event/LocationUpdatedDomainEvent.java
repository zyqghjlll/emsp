package com.ethan.emsp.domain.event;

import com.ethan.emsp.core.ddd.AppEvent;
import lombok.Getter;

@Getter
public class LocationUpdatedDomainEvent extends AppEvent {
    private final String id;
    private final String name;

    public LocationUpdatedDomainEvent(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
