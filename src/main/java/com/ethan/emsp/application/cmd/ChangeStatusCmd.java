package com.ethan.emsp.application.cmd;

import com.ethan.emsp.domain.model.evse.EvseId;
import com.ethan.emsp.domain.model.evse.EvseStatus;

public record ChangeStatusCmd(
        EvseId evseId,
        EvseStatus newStatus
) {
}
