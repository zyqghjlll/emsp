package com.ethan.emsp.infrastructure.common;

import com.ethan.emsp.core.result.exception.InvalidArgumentException;

public record LocalEvseId(String value) {
    public LocalEvseId {
        if (value == null || value.isBlank()) {
            throw new InvalidArgumentException("LocalEvseId cannot be empty");
        }
    }

    public static LocalEvseId of(String value) {
        return new LocalEvseId(value);
    }
}
