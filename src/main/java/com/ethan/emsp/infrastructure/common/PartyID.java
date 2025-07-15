package com.ethan.emsp.infrastructure.common;

import com.ethan.emsp.core.result.exception.InvalidArgumentException;

public enum PartyID {
    ABC("ABC"),
    ABB("ABB"),
    EEE("EEE"),

    ;
    private final String code;

    PartyID(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static PartyID fromCode(String code) {
        for (PartyID value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        throw new InvalidArgumentException("Invalid PartyID code: " + code);
    }
}
