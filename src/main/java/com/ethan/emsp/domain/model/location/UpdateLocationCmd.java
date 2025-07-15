package com.ethan.emsp.domain.model.location;

public record UpdateLocationCmd(
        LocationId id,
        String name,
        String address,
        BusinessHours businessHours
) {

}
