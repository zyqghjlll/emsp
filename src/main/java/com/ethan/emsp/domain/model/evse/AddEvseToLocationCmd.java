package com.ethan.emsp.domain.model.evse;

import com.ethan.emsp.domain.model.location.LocalEvseId;
import com.ethan.emsp.domain.model.location.LocationId;
import com.ethan.emsp.infrastructure.common.CountryCode;
import com.ethan.emsp.infrastructure.common.PartyID;

public record AddEvseToLocationCmd(
        LocationId locationId,
        EvseId evseId
) {
}
