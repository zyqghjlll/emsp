package com.ethan.emsp.domain.model.location;

import com.ethan.emsp.core.result.exception.InvalidArgumentException;

public record CreateLocationCmd(
        String name,
        String address,
        Coordinate coordinate,
        BusinessHours businessHours
) {
    public CreateLocationCmd(String name, String address, Coordinate coordinate, BusinessHours businessHours) {
        if (name == null || name.isEmpty()) {
            throw new InvalidArgumentException("Location name cannot be empty");
        }
        if (address == null || address.isEmpty()) {
            throw new InvalidArgumentException("Location address cannot be empty");
        }
        if (coordinate == null) {
            throw new InvalidArgumentException("Location coordinate cannot be empty");
        }
        if (businessHours == null) {
            throw new InvalidArgumentException("Location businessHours cannot be empty");
        }
        this.name = name;
        this.address = address;
        this.coordinate = coordinate;
        this.businessHours = businessHours;
    }
}