package com.ethan.emsp.core.result.exception;

import com.ethan.emsp.core.result.ResultCode;

public class BusinessException extends BaseException {
    public BusinessException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }
}
