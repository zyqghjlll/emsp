package com.ethan.emsp.application.cmd;

import com.ethan.emsp.domain.model.evse.EvseId;
import com.ethan.emsp.domain.model.location.LocationId;

public record AddEvseToLocationCmd(
        LocationId locationId,
        EvseId evseId
) {
}
