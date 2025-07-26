package com.ethan.emsp.domain.model.location;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationId that = (LocationId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

