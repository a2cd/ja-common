package org.caseor.common.core.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.caseor.common.core.enums.HttpCode;

/**
 * 业务异常
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private HttpCode httpCode;

    private Object data;

    private String msg;

    /**
     * 空构造方法，避免反序列化问题
     */
    public ServiceException() {
    }

    public ServiceException(String msg) {
        super(msg);
        this.httpCode = HttpCode.INVALID_REQUEST;
        this.msg = msg;
    }

    public ServiceException(HttpCode httpCode, String msg) {
        super(msg);
        this.httpCode = httpCode;
        this.msg = msg;
    }

    public ServiceException(Object data, String msg) {
        super(msg);
        this.httpCode = HttpCode.INVALID_REQUEST;
        this.data = data;
        this.msg = msg;
    }

    public ServiceException(HttpCode httpCode, Object data, String msg) {
        super(msg);
        this.httpCode = httpCode;
        this.data = data;
        this.msg = msg;
    }

}
