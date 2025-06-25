package com.ethan.emsp.domain.location;

import com.ethan.emsp.core.ddd.Aggregate;
import com.ethan.emsp.domain.evse.EVSE;

import java.lang.annotation.Retention;
import java.time.LocalDateTime;
import java.util.*;

public class Location implements Aggregate {
    private String id;
    private String name;
    private String address;
    private String coordinates;
    private String businessHours;
    private List<EVSE> EVSEList;
    private LocalDateTime createdAt;

    public Location(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public String getId() {
        return this.id;
    }

    public static Location create(String name, String address) {
        String id = UUID.randomUUID().toString();
        return new Location(id, name, address);
    }
}
