package com.ethan.emsp.core.result;

public enum ResultCode {
    // 200 成功
    SUCCESS(200, "Success"),

    // 400 系列客户端错误
    INVALID_ARGUMENT(400, "Invalid request parameter"),
    UNAUTHORIZED(401, "Unauthorized access"),
    FORBIDDEN(403, "Forbidden access"),
    NOT_FOUND(404, "Resource not found"),
    CONFLICT(409, "Resource already exists"),
    BUSINESS_LIMIT(410, "Reached some business limit"),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported media type"),
    TOO_MANY_REQUESTS(429, "Too many requests"),

    // 500 系列服务端错误
    SERVER_ERROR(500, "Internal server error"),
    NOT_IMPLEMENTED(501, "Not implemented"),
    BAD_GATEWAY(502, "Bad gateway"),
    SERVICE_UNAVAILABLE(503, "Service unavailable"),
    GATEWAY_TIMEOUT(504, "Gateway timeout"),

    // -------------------------------------------------------------------------------------------------------------------

    // 通用默认
    UNEXPECTED_ERROR(5000, "An unexpected error occurred. Please contact support."),

    DATA_ACCESS_ERROR(5001, "Database unavailable"),
    EXTERNAL_SYSTEM_ERROR(5002, "External system unavailable"),

    ;
    private final int code;
    private final String description;

    ResultCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
