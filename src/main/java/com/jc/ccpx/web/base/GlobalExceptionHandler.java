package com.jc.ccpx.web.base;

import com.jc.ccpx.exception.CcpxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    public String exceptionHandler(HttpServletRequest request, Exception e){
        log.error("统一异常拦截 request:{}, exception:{}", "", e);
        if(e instanceof CcpxException){
            return "服务异常:"+ e.getMessage();
        }
        return "服务器繁忙，请稍后再试!";
    }

}
