package com.ethan.emsp.api.controller.dto;

import com.ethan.emsp.domain.model.evse.ChangeStatusCmd;
import com.ethan.emsp.domain.model.evse.CreateEvseCmd;
import com.ethan.emsp.domain.model.evse.EvseId;
import com.ethan.emsp.domain.model.evse.EvseStatus;
import com.ethan.emsp.domain.model.location.LocalEvseId;
import com.ethan.emsp.domain.model.location.LocationId;
import com.ethan.emsp.infrastructure.common.CountryCode;
import com.ethan.emsp.infrastructure.common.PartyID;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class ChangeStatusDto implements Serializable {
    @NotBlank(message = "EVSE ID cannot be blank")
    private String evseId;
    @NotBlank(message = "EVSE status cannot be blank")
    private String newStatus;

    public ChangeStatusCmd toCommand() {
        return new ChangeStatusCmd(
                EvseId.of(evseId),
                EvseStatus.fromCode(newStatus)
        );
    }
}

