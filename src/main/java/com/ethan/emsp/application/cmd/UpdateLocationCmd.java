package com.ethan.emsp.application.cmd;

import com.ethan.emsp.domain.model.location.BusinessHours;
import com.ethan.emsp.domain.model.location.LocationId;

public record UpdateLocationCmd(
        LocationId id,
        String name,
        String address,
        BusinessHours businessHours
) {

}
