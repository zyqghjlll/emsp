package com.ethan.emsp.core.result.exception;

import com.ethan.emsp.core.result.ResultCode;

public class PersistenceException extends BaseException {
    private static final ResultCode resultCode = ResultCode.DATA_ACCESS_ERROR;

    public PersistenceException(String message) {
        super(resultCode, message);
    }
}
