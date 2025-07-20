package com.ethan.emsp.application.cmd;

import com.ethan.emsp.infrastructure.common.LocalEvseId;
import com.ethan.emsp.infrastructure.common.CountryCode;
import com.ethan.emsp.infrastructure.common.PartyID;

public record CreateEvseCmd(
        CountryCode countryCode,
        PartyID partyId,
        LocalEvseId localEvseId
) {
}
