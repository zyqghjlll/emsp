package com.ethan.emsp.domain.connector;

import com.ethan.emsp.core.ddd.ValueObject;
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
            throw new IllegalArgumentException("Invalid connector parameters");
        }
    }
}
