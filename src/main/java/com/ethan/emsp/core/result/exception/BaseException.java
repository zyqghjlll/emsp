package com.ethan.emsp.core.result.exception;

import com.ethan.emsp.core.result.ResultCode;

public class BaseException extends RuntimeException {
    private final ResultCode resultCode;
    private final String message;

    public BaseException(ResultCode resultCode, String message) {
        super(resultCode.getDescription());
        this.resultCode = resultCode;
        this.message = message;
    }

    public ResultCode getErrorCode() {
        return resultCode;
    }

    public String getMessage() {
        return message;
    }
}
