package com.ngawang.zepa.enumeration;

public enum LoginErrorCode {
    //region enum
    FAILED("userLogins.failed"),
    LOCKED("userLogins.locked"),
    MAX_SESSION("userLogins.maxSession"), ;
    //endregion
//region private variables
    private final String code;
    //endregion

    //region method
    private LoginErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    //endregion
}
