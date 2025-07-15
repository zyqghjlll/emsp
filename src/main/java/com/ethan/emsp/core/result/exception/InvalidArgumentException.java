package com.ethan.emsp.core.result.exception;

import com.ethan.emsp.core.result.ResultCode;

public class InvalidArgumentException extends BaseException {
    private static final ResultCode resultCode = ResultCode.INVALID_ARGUMENT;

    public InvalidArgumentException(String message) {
        super(resultCode, message);
    }
}
