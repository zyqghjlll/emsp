package com.ethan.emsp.infrastructure.common;

import com.ethan.emsp.core.result.exception.InvalidArgumentException;

public enum CountryCode {
    CN("CN"),
    US("US"),
    HK("HK"),


    ;
    private final String code;

    CountryCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static CountryCode fromCode(String code) {
        for (CountryCode value : values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        throw new InvalidArgumentException("Invalid country code: " + code);
    }
}
