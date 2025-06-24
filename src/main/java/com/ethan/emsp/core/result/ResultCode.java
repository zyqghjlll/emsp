package com.ethan.emsp.core.result;

public enum ResultCode {
    SUCCESS("0000", "成功"),
    FAIL("0001", "失败"),
    NOT_FOUND("0002", "未找到"),
    NOT_AUTH("0003", "未授权"),
    NOT_LOGIN("0004", "未登录"),
    NOT_SUPPORT("0005", "不支持"),
    NOT_ALLOWED("0006", "不允许"),
    NOT_EXIST("0007", "不存在"),
    NOT_VALID("0008", "无效"),
    NOT_SUPPORT_OPERATION("0009", "不支持的操作"),

    ;
    private final String code;
    private final String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ResultCode getByCode(String code) {
        for (ResultCode resultCode : values()) {
            if (resultCode.code.equals(code)) {
                return resultCode;
            }
        }
        return null;
    }
}
