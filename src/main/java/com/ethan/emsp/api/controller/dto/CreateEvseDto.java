package com.ethan.emsp.api.controller.dto;

import com.ethan.emsp.domain.model.evse.CreateEvseCmd;
import com.ethan.emsp.domain.model.location.LocalEvseId;
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

    public CreateEvseCmd toCommand() {
        return new CreateEvseCmd(
                CountryCode.fromCode(countryCode),
                PartyID.fromCode(partyId),
                LocalEvseId.of(localEvseId)
        );
    }
}

