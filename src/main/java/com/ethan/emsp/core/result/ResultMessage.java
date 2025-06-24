package com.ethan.emsp.core.result;

import lombok.Data;

@Data
public class ResultMessage<T> {
    private final String code;
    private final String message;
    private final T data;

    public ResultMessage(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultMessage<T> success(T data) {
        return new ResultMessage<T>("200", "success", data);
    }

    public static <T> ResultMessage<T> fail(T data) {
        return new ResultMessage<T>("500", "fail", data);
    }
}
