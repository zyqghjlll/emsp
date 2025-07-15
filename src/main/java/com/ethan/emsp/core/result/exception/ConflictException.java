package com.ethan.emsp.core.result.exception;

import com.ethan.emsp.core.result.ResultCode;

public class ConflictException extends BaseException {
    public ConflictException(String message) {
        super(ResultCode.CONFLICT, message);
    }
}
