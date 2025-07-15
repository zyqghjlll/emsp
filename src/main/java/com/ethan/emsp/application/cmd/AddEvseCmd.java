package com.ethan.emsp.application.cmd;

import com.ethan.emsp.core.result.exception.InvalidArgumentException;
import com.ethan.emsp.domain.model.evse.EvseId;
import com.ethan.emsp.domain.model.evse.EvseStatus;
import com.ethan.emsp.domain.model.location.LocalEvseId;
import com.ethan.emsp.domain.model.location.LocationId;
import com.ethan.emsp.infrastructure.common.CountryCode;
import com.ethan.emsp.infrastructure.common.PartyID;

public record AddEvseCmd(
        LocationId locationId,
        EvseId evseId
) {
    public AddEvseCmd(LocationId locationId, EvseId evseId) {
        if (locationId == null || evseId == null) {
            throw new InvalidArgumentException("LocationId and evseId cannot be null");
        }
        this.locationId = locationId;
        this.evseId = evseId;
    }
}
