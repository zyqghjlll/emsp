package com.ethan.emsp.api.controller.dto;

import com.ethan.emsp.domain.model.evse.AddConnectorCmd;
import com.ethan.emsp.domain.model.evse.Connector;
import com.ethan.emsp.domain.model.evse.EvseId;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class AddConnectorDto implements Serializable {
    @NotBlank(message = "EVSE ID cannot be blank")
    private String evseId;
    private String type;
    private int voltage;
    private int amperage;
    private double power;
    private String standard;

    public AddConnectorCmd toCommand() {
        return new AddConnectorCmd(
                EvseId.of(evseId),
                new Connector(type, voltage, amperage, power, standard)
        );
    }
}

