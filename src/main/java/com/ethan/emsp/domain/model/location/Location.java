package com.ethan.emsp.domain.model.location;

import com.ethan.emsp.core.ddd.AggregateRoot;
import com.ethan.emsp.core.result.ResultCode;
import com.ethan.emsp.core.result.exception.BusinessException;
import com.ethan.emsp.core.result.exception.NotFoundException;

import java.util.*;

public class Location implements AggregateRoot<LocationId> {
    private final LocationId id;
    private LocationAttributes attributes;

    public Location(LocationId id, LocationAttributes attributes) {
        this.id = Objects.requireNonNull(id, "Location ID must not be null");
        this.attributes = Objects.requireNonNull(attributes, "Location attributes must not be null");
    }

    @Override
    public LocationId getId() {
        return this.id;
    }

    public LocationAttributes getAttributes() {
        return this.attributes;
    }

    public void updateAttributes(LocationAttributes attributes) {
        this.attributes = attributes;
    }
}
