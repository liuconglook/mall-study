package com.belean.mall.tiny.config.exception;

import com.belean.mall.tiny.common.api.CommonResult;
import com.belean.mall.tiny.common.api.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 * Created by belean on 2021/7/25.
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局异常处理，返回JSON
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    public CommonResult globalException(HttpServletRequest request, GlobalException exception) {
        log.error("全局的异常消息：{}", exception.getMessage());
        return new CommonResult(exception.getCode(), exception.getMessage(), null);
    }

    /**
     * 未授权异常处理，返回JSON
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseBody
    public CommonResult accessDeniedException(HttpServletRequest request, AccessDeniedException exception) {
        log.error("全局的异常消息：{}", exception.getMessage());
        return CommonResult.forbidden(null);
    }

    /**
     * 未知或未捕捉到的异常，统一返回失败
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResult exception(HttpServletRequest request, Exception exception) {
        log.error("未处理的异常消息：{}", exception);
        return CommonResult.failed();
    }

}
