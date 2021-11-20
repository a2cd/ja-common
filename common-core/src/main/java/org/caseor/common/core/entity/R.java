package org.caseor.common.core.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.caseor.common.core.enums.HttpCode;

import java.io.Serializable;

/**
 * 响应信息主体
 */

@Data
@AllArgsConstructor
@ToString
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private T data;

    private String msg;

    public static <T> R<T> success() {
        return new R<>(HttpCode.SUCCESS.getCode(), null, HttpCode.SUCCESS.getMsg());
    }

    public static <T> R<T> success(String msg) {
        return new R<>(HttpCode.SUCCESS.getCode(), null, msg);
    }

    public static <T> R<T> success(T data) {
        return new R<>(HttpCode.SUCCESS.getCode(), data, HttpCode.SUCCESS.getMsg());
    }

    public static <T> R<T> success(T data, String msg) {
        return new R<>(HttpCode.SUCCESS.getCode(), data, msg);
    }

    public static <T> R<T> success(HttpCode httpCode, T data, String msg) {
        return new R<>(httpCode.getCode(), data, msg);
    }

    public static <T> R<T> failure() {
        return new R<>(HttpCode.INVALID_REQUEST.getCode(), null, HttpCode.INVALID_REQUEST.getMsg());
    }

    public static <T> R<T> failure(String msg) {
        return new R<>(HttpCode.INVALID_REQUEST.getCode(), null, msg);
    }

    public static <T> R<T> failure(T data) {
        return new R<>(HttpCode.INVALID_REQUEST.getCode(), data, HttpCode.INVALID_REQUEST.getMsg());
    }

    public static <T> R<T> failure(T data, String msg) {
        return new R<>(HttpCode.INVALID_REQUEST.getCode(), data, msg);
    }

    public static <T> R<T> failure(HttpCode httpCode, T data, String msg) {
        return new R<>(httpCode.getCode(), data, msg);
    }

}
