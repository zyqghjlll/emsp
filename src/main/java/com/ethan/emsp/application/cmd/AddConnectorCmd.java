package com.ethan.emsp.application.cmd;

import com.ethan.emsp.domain.model.evse.Connector;
import com.ethan.emsp.domain.model.evse.EvseId;

public record AddConnectorCmd(
        EvseId evseId,
        Connector connector
) {
}
