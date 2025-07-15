package com.ethan.emsp.domain.model.evse;

public record ChangeStatusCmd(
        EvseId evseId,
        EvseStatus newStatus
) {
}
