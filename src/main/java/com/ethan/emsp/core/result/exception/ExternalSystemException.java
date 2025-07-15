package com.ethan.emsp.core.result.exception;


import com.ethan.emsp.core.result.ResultCode;

public class ExternalSystemException extends BaseException {
    public ExternalSystemException(String message) {
        super(ResultCode.EXTERNAL_SYSTEM_ERROR, message);
    }
}