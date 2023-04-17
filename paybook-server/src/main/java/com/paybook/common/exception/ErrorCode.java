package com.paybook.common.exception;

public enum ErrorCode {
    DUPLICATED_USERNAME(400, "AU_001", "이미 존재하는 이름입니다."),
    DUPLICATED_EMAIL(400, "AU_002", "이미 존재하는 E-mail입니다."),
    USER_ROLE_NOT_SET(400, "AU_003", "User Role not set."),

    PAYMENT_OPTION_NOT_FOUND(400, "PO_001", "결재방법을 찾을 수 없습니다."),
    ;

    private final String code;

    private final String message;

    private final int status;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
