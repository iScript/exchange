package com.ykq.counter.controller;

import com.ykq.counter.bean.res.CounterRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public CounterRes exceptionHandler(
            HttpServletRequest request,Exception e
    ){
        log.error("",e);
        return new CounterRes(CounterRes.FAIL,
                "发生错误",
                null);
    }

}
