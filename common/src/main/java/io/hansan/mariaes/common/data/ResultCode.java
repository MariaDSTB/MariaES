package io.hansan.mariaes.common.data;

public enum ResultCode implements IErrorCode{
    SUCCESS(200, "Operation Success"),
    FAILED(200, "Operation Failed"),
    UNAUTHORIZED(401, "Not logged in or your token has expired"),
    FORBIDDEN(403, "There are no relevant permissions"),
    VALIDATE_FAILED(404, "Parameter verification failed");
    private long  code;
    private String message;
    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }
    public long getCode(){
        return code;
    }
    public String getMessage() {
        return message;
    }
}
