package io.hansan.mariaes.common.data;

public enum ResultCode implements IErrorCode{
    SUCCESS(200, "操作成功"),
    FAILED(200, "操作失败"),
    UNAUTHORIZED(401, "暂未登录或token已过期"),
    FORBIDDEN(403, "没有相关权限"),
    VALIDATE_FAILED(404, "参数校验失败");
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
