package com.ethan.emsp.core.result.exception;

import com.ethan.emsp.core.result.ResultCode;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(ResultCode.NOT_FOUND, message);
    }
}
