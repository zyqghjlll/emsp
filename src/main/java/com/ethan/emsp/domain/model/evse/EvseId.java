package com.ethan.emsp.domain.model.evse;

import com.ethan.emsp.core.result.exception.InvalidArgumentException;
import com.ethan.emsp.infrastructure.common.CountryCode;
import com.ethan.emsp.infrastructure.common.LocalEvseId;
import com.ethan.emsp.infrastructure.common.PartyID;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public class EvseId {
    private final CountryCode countryCode;
    private final PartyID partyId;
    private final LocalEvseId localEvseId;

    private static final Pattern PATTERN = Pattern.compile("^[A-Z]{2}\\*[A-Z0-9]{3}\\*\\w{1,30}$");

    public EvseId(String value) {
        if (!PATTERN.matcher(value).matches()) {
            throw new InvalidArgumentException("Invalid Evse ID format: " + value + ", expect: US*ABC*EVSE123456");
        }
        String[] parts = value.split("\\*");
        this.countryCode = CountryCode.fromCode(parts[0]);
        this.partyId = PartyID.fromCode(parts[1]);
        this.localEvseId = LocalEvseId.of(parts[2]);
    }

    public static EvseId of(String id) {
        return new EvseId(id);
    }

    public static EvseId of(CountryCode countryCode, PartyID partyId, LocalEvseId localEvseId) {
        return new EvseId(buildId(countryCode, partyId, localEvseId));
    }

    private static String buildId(CountryCode countryCode, PartyID partyId, LocalEvseId localEvseId) {
        return countryCode + "*" + partyId + "*" + localEvseId.value();
    }

    public String getValue() {
        return buildId(countryCode, partyId, localEvseId);
    }
}
