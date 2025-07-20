package com.ethan.emsp.domain.model.location;

public final class LocationId {
    private final String value;

    private static final LocationId EMPTY = new LocationId("");

    private LocationId(String value) {
        this.value = value;
    }

    public static LocationId of(String value) {
        if (value == null || value.isBlank()) {
            return EMPTY;
        }
        return new LocationId(value);
    }

    public static LocationId empty() {
        return EMPTY;
    }

    public String getValue() {
        return value;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }
}

