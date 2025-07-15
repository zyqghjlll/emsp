package com.ethan.emsp.domain.model.evse;

import com.ethan.emsp.core.ddd.ValueObject;
import com.ethan.emsp.core.result.exception.InvalidArgumentException;
import lombok.AllArgsConstructor;

public record Connector(
        String type,
        int voltage,
        int amperage,
        double power,
        String standard
) implements ValueObject {
    public Connector {
        if (voltage <= 0 || amperage <= 0 || power <= 0) {
            throw new InvalidArgumentException("Invalid connector parameters");
        }
    }
}
