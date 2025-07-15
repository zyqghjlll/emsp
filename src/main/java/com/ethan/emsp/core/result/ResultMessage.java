package com.ethan.emsp.core.result;

public record ResultMessage<T>(
        int code,
        String message,
        String detail,
        T data
) {
    private ResultMessage() {
        this(0, null, null, null);
    }

    // 成功
    public static <T> ResultMessage<T> success(T data) {
        return new ResultMessage<>(
                ResultCode.SUCCESS.getCode(),
                ResultCode.SUCCESS.getDescription(),
                null,
                data
        );
    }

    public static <T> ResultMessage<T> success() {
        return success(null);
    }

    // 简单失败
    public static <T> ResultMessage<T> failure(ResultCode resultCode) {
        return new ResultMessage<>(
                resultCode.getCode(),
                resultCode.getDescription(),
                null,
                null
        );
    }

    // 带详细信息的失败
    public static <T> ResultMessage<T> failure(ResultCode resultCode, String detail) {
        return new ResultMessage<>(
                resultCode.getCode(),
                resultCode.getDescription(),
                detail,
                null
        );
    }

    // 特殊需要可返回带 data 的失败
    public static <T> ResultMessage<T> failure(ResultCode resultCode, String detail, T data) {
        return new ResultMessage<>(
                resultCode.getCode(),
                resultCode.getDescription(),
                detail,
                data
        );
    }
}
