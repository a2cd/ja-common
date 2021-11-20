package org.caseor.common.core.enums;

/**
 * @author Fu Kai
 * @since 20211019
 */

public enum HttpCode {
    /**
     * 请求成功, 一般用于GET与POST请求
     */
    SUCCESS(200, "SUCCESS", "Success"),
    /**
     * 已创建, 成功请求并创建了新的资源
     */
    CREATED(201, "CREATED", "Created"),
    /**
     * 已接受, 已经接受请求, 但未处理完成
     */
    ACCEPTED(202, "ACCEPTED", "Accepted"),
    /**
     * 无效请求, 用户发出的请求有错误，服务器没有进行新建或修改数据的操作
     */
    INVALID_REQUEST(400, "INVALID_REQUEST", "Invalid Request"),
    /**
     * 未授权
     */
    UNAUTHORIZED(401, "UNAUTHORIZED", "Unauthorized"),
    /**
     * 服务器理解请求客户端的请求, 但是拒绝执行此请求
     */
    FORBIDDEN(403, "FORBIDDEN", "Forbidden"),
    /**
     * 未找到
     */
    NOT_FOUND(404, "NOT_FOUND", "Not Found"),
    /**
     * 请求失败, 服务端内部错误
     */
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "Internal Server Error"),
    /*
     * 不合法的token
     */
    ILLEGAL_TOKEN(50008, "ILLEGAL_TOKEN", "Illegal Token"),
    /**
     * 角色发生改变
     */
    ROLE_CHANGED(50012, "ROLE_CHANGED", "Role Changed"),
    /**
     * token过期
     */
    TOKEN_EXPIRED(50014, "TOKEN_EXPIRED", "Token Expired");


    private final Integer code;
    private final String status;
    private final String msg;

    HttpCode(Integer code, String status, String msg) {
        this.code = code;
        this.status = status;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
