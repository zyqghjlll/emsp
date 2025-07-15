package com.ethan.emsp.api.controller.dto;

import cn.hutool.core.util.ObjectUtil;
import com.ethan.emsp.domain.model.evse.CreateEvseCmd;
import com.ethan.emsp.domain.model.evse.EvseStatus;
import com.ethan.emsp.domain.model.location.*;
import com.ethan.emsp.infrastructure.common.CountryCode;
import com.ethan.emsp.infrastructure.common.PartyID;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateEvseDto implements Serializable {
    @NotBlank(message = "Country code cannot be blank")
    private String countryCode;
    @NotBlank(message = "Party ID cannot be blank")
    private String partyId;
    @NotBlank(message = "Local EVSE ID cannot be blank")
    private String localEvseId;
    private String locationId;

    public CreateEvseCmd toCommand() {
        return new CreateEvseCmd(
                CountryCode.fromCode(countryCode),
                PartyID.fromCode(partyId),
                LocalEvseId.of(localEvseId),
                LocationId.of(locationId)
        );
    }
}

