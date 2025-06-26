package com.ethan.emsp.domain.location;

import com.ethan.emsp.core.ddd.AggregateRoot;
import com.ethan.emsp.domain.evse.EVSE;

import java.util.*;

public class Location implements AggregateRoot {
    private final String id;
    private final LocationAttributes attributes;
    private final List<EVSE> evseHolder;

    public Location(String id, LocationAttributes attributes) {
        this.id = Objects.requireNonNull(id, "Location ID must not be null");
        this.attributes = Objects.requireNonNull(attributes, "Location attributes must not be null");
        evseHolder = new ArrayList<>();
    }

    @Override
    public String getId() {
        return this.id;
    }

    public LocationAttributes getAttributes() {
        return this.attributes;
    }

    public void addEvse(EVSE evse) {
        // 校验唯一性
        boolean exists = evseHolder.stream().anyMatch(e -> e.getId().equals(evse.getId()));
        if (exists) {
            throw new IllegalArgumentException("EVSE with same ID already exists");
        }
        evseHolder.add(evse);
    }

    public List<EVSE> getEvseList() {
        return new ArrayList<>(this.evseHolder); // 返回副本防止外部修改
    }
}
