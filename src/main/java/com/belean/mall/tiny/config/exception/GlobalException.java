package com.belean.mall.tiny.config.exception;

import com.belean.mall.tiny.common.api.IErrorCode;

/**
 * 全局异常
 * Created by belean on 2021/7/25.
 **/
public class GlobalException extends RuntimeException {

    private long code;

    private String message;

    /**
     * 传递 自定义、已知的 异常枚举类
     * @param iErrorCode
     */
    public GlobalException(IErrorCode iErrorCode){
        super(iErrorCode.getMessage());
        this.code = iErrorCode.getCode();
        this.message = iErrorCode.getMessage();
    }

    /**
     * 传递 异常枚举之外的 或 需要细分的 异常码和信息
     * @param code
     * @param message
     */
    public GlobalException(long code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }

    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
