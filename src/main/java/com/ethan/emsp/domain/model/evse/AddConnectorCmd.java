package com.ethan.emsp.domain.model.evse;

public record AddConnectorCmd(
        EvseId evseId,
        Connector connector
) {
}
