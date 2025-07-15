package com.ethan.emsp.api.controller.dto;

import com.ethan.emsp.domain.model.evse.AddEvseToLocationCmd;
import com.ethan.emsp.domain.model.evse.CreateEvseCmd;
import com.ethan.emsp.domain.model.evse.EvseId;
import com.ethan.emsp.domain.model.evse.EvseStatus;
import com.ethan.emsp.domain.model.location.LocalEvseId;
import com.ethan.emsp.domain.model.location.LocationId;
import com.ethan.emsp.infrastructure.common.CountryCode;
import com.ethan.emsp.infrastructure.common.PartyID;
import lombok.Data;

import java.io.Serializable;

@Data
public class AddEvseToLocationDto implements Serializable {
    private String locationId;
    private String evseId;

    public AddEvseToLocationCmd toCommand() {
        return new AddEvseToLocationCmd(
                LocationId.of(locationId),
                EvseId.of(evseId)
        );
    }
}

